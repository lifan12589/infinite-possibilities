package com.infinitePossibilities.utils;

/**
 * 1bit+41位时间戳+5bit业务+7bit机器ID+10序列
 * 1.得到每个组成部分的值跟长度
 * 2.得到位移数
 * 3.套用公式
 * 4.去实现序列号就可以了
 */
public class SnowFlakeIdService {

    //系统上线时间
    private final long startTime = 1604833161000L;
    //业务线类型
    private long businessType;
    //机器ID
    private long workId;
    //序列号
    private long serialNum = 0L;

    //region 得到长度
    private  final long serialNumBits = 10L;
    private final long workBits = 7L;
    private final long businessTypeBits = 5L;
    // 得到左位移数
    private final long workShift = serialNumBits;
    private final long businessTypeShift = workShift + workBits;
    private final long timeStampShift = businessTypeShift + businessTypeBits;
    //endregion

    private long lastTimeStamp = 0L; //上一次的执行时间

    //获取serialNum的最大值
    private final long serialNumMax = -1^(-1L<<serialNumBits);


    public SnowFlakeIdService(long businessType, long workId) {
        this.businessType = businessType;
        this.workId = workId;
    }

    public synchronized long getId() {
        // TODO: 2020/11/8 校验
        long timeStamp = System.currentTimeMillis();
        //同一毫秒的时候去递增
        if (lastTimeStamp == timeStamp) {
            //serialNum+1,判断是否超过最大值
            serialNum = (serialNum + 1) & serialNumMax;

            if (serialNum == 0) {
                //超过最大值了,怎么办 延时下一毫秒  要么报错！！！
                timeStamp = waitNextMillis(timeStamp);
            }
        }else {
            serialNum = timeStamp & 1;   //  随机 0 跟1   毫秒数（动的）的最后1位
        }
        lastTimeStamp = timeStamp;
        return ((timeStamp - startTime) << timeStampShift)
                | (businessType << businessTypeShift)
                | (workId << workShift)
                | serialNum;


    }

    //获取下一毫秒的时间
    private long waitNextMillis(long timeStamp) {
        long nowTimeStamp = System.currentTimeMillis();
        while (timeStamp >= nowTimeStamp) {

            nowTimeStamp = System.currentTimeMillis();
        }
        return nowTimeStamp;
    }


    public static void main(String[] args) {
        SnowFlakeIdService sfis = new SnowFlakeIdService(31000,3100000);
        for (int i = 0; i <2000 ; i++) {
            System.out.println(sfis.getId());
        }



    }


}
