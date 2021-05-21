package com.wondersgroup.utils.threads.duoThread;

import java.util.Random;
import java.util.concurrent.Phaser;

public class ThreadCo4 {


    static final int COUNT = 6;

    public static void main(String[] args) throws Exception {
        new Thread(new Challenger("张三")).start();
        new Thread(new Challenger("李四")).start();
        new Thread(new Challenger("王五")).start();
        new Thread(new Challenger("赵六")).start();
        new Thread(new Challenger("大胖")).start();
        new Thread(new Challenger("小白")).start();
        synchronized (ThreadCo4.class) {
            ThreadCo4.class.wait();
        }
    }

    static Phaser ph = new Phaser() {

        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println(String.format("第(%d)局，剩余[%d]人", phase, registeredParties));
            return registeredParties == 0 ||
                    (phase != 0 && registeredParties == COUNT);
        };
    };

    static class Challenger implements Runnable {

        String name;
        int state;

        Challenger(String name) {
            this.name = name;
            this.state = 0;
        }

        @Override
        public void run() {
            System.out.println(String.format("[%s]开始挑战。。。", name));
            ph.register();
            int phase = 0;
            int h;
            while (!ph.isTerminated() && phase < 100) {
//                doingLongTime(5);
                if (state == 0) {
                    if (Decide.goon()) {
                        h = ph.arriveAndAwaitAdvance();
                        if (h < 0)
                            System.out.println(String.format("No%d.[%s]继续，但已胜利。。。", phase, name));
                        else
                            System.out.println(String.format("No%d.[%s]继续at(%d)。。。", phase, name, h));
                    } else {
                        state = -1;
                        h = ph.arriveAndDeregister();
                        System.out.println(String.format("No%d.[%s]退出at(%d)。。。", phase, name, h));
                    }
                } else {
                    if (Decide.revive()) {
                        state = 0;
                        h = ph.register();
                        if (h < 0)
                            System.out.println(String.format("No%d.[%s]复活，但已失败。。。", phase, name));
                        else
                            System.out.println(String.format("No%d.[%s]复活at(%d)。。。", phase, name, h));
                    } else {
                        System.out.println(String.format("No%d.[%s]没有复活。。。", phase, name));
                    }
                }
                phase++;
            }
            if (state == 0) {
                ph.arriveAndDeregister();
            }
            System.out.println(String.format("[%s]结束。。。", name));
        }

    }

    static class Decide {

        static boolean goon() {
            return random(9) > 4;
        }

        static boolean revive() {
            return random(9) < 5;
        }
    }
    public static int random(int max) {
        return random(0, max);
    }

    public static int random(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }

}
