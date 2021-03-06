package producer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.wondersgroup.ClientApplication;
import com.wondersgroup.sender.RabbitSender;

@SpringBootTest(classes = ClientApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

	// 并发量
	private static final int USER_NUM = 10;
	// 倒计时器，用于模拟高并发
	private static CountDownLatch cdl = new CountDownLatch(USER_NUM);
	
	@Autowired
	private RabbitSender rabbitSender;

	@Test
	public void testRabbit() {
		String queueName = "queue";
		String orderId = "123456";
		rabbitSender.send(queueName,orderId);
	}
	@Test
	public void testRabbitObject() {
		String queueName = "queueObject";
		Map<String,Object> user = new HashMap<String,Object>(); // 实现Serializable接口
		user.put("name", "张三");
		user.put("age", 24);
		rabbitSender.sendObject(queueName,user);
	}
	@Test
	public void testRabbitTopic() {
		String exchange = "exchange";
		String queueName = "topic.order";
		String orderId = "123456";
		rabbitSender.sendTopic(exchange,queueName,orderId);
	}
	
	
    //并发模拟
	@Test
	public void testStressRabbitTopic() throws InterruptedException {
		// 循环实例化USER_NUM个并发请求（线程）
		for (int i = 0; i < USER_NUM; i++) {
			new Thread(new UserRequst()).start();
			System.out.println("减一之前的线程："+Thread.currentThread().getName());
			cdl.countDown();// 倒计时器减一
		}
		
		Thread.currentThread().sleep(2000);

	}
	
	// 内部类继承线程接口，用于模拟用户请求
		public class UserRequst implements Runnable {		
			@Override
			public void run() {
				try {
					System.out.println("即将等待的线程："+Thread.currentThread().getName());
					cdl.await();// 当前线程等待，等所以线程实例化完成后，同时停止等待后调用接口代码
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String exchange = "exchange";
				String queueName = "topic.order";
				String orderId = "123456";
				rabbitSender.sendTopic(exchange,queueName,orderId);
			}
		}
	}