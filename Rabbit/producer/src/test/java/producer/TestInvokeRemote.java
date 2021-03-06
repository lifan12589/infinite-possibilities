package producer;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import com.wondersgroup.ClientApplication;

@SpringBootTest(classes = ClientApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInvokeRemote {

	RestTemplate restTemplate = new RestTemplate();
	// 并发量
	private static final int USER_NUM = 5;
	// 倒计时器，用于模拟高并发
	private static CountDownLatch cdl = new CountDownLatch(USER_NUM);
	private final String url = "http://127.0.0.1:8090/queryOrderInfo?orderId=123456";

	@Test
	public void testInvokeRemote() throws InterruptedException {
		// 循环实例化USER_NUM个并发请求（线程）
		for (int i = 0; i < USER_NUM; i++) {
			new Thread(new UserRequst()).start();
			cdl.countDown();// 倒计时器减一
		}
		Thread.currentThread().sleep(2000);
   }

	// 内部类继承线程接口，用于模拟用户请求
	public class UserRequst implements Runnable {		
		@Override
		public void run() {
			try {
				cdl.await();// 当前线程等待，等所以线程实例化完成后，同时停止等待后调用接口代码
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String str = restTemplate.getForEntity(url, String.class).getBody();
			System.out.println(str);
		}
	}
}