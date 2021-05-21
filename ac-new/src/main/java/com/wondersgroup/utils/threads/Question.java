package com.wondersgroup.utils.threads;


import java.util.LinkedList;
import java.util.List;
class Contact {
    volatile List<Object> list = new LinkedList<Object>();
    void add(Object o) {
        list.add(o);
    }
    int size() {
        return list.size();
    }
}
public class Question {
     static volatile boolean t2Started = false;
	 static Contact contact = new Contact();
     static Object ob = new Object();
     
    public static void main(String[] args) {

        //原程序
        Thread thread1 = new Thread(new Runnable() {
            public void run() {

                synchronized(ob) {
                    for (int i = 0; i < 10; i++) {
                        contact.add(new Object());
                        System.out.println("thread1 add " + contact.size() + "th element");
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (contact.size() == 5) {
                            ob.notify();
                            try {
                                ob.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {

                synchronized(ob) {
                    if (contact.size() != 5) {
                        try {
                            ob.notify();
                            ob.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("thread2 ");
                        ob.notify();
                    }
                }
            }
        });
        thread2.start(); thread1.start();





//        Thread thread1 = new Thread(new Runnable() {
//            public void run() {
//
//                synchronized(ob) {
//
//                    while(!t2Started) {
//                        try {
//                            ob.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                    for (int i = 0; i < 10; i++) {
//                        contact.add(new Object());
//                        System.out.println("thread1 add " + contact.size() + "th element");
//                        try {
//                            Thread.sleep(100);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        if (contact.size() == 5) {
//                            ob.notify();
//                            try {
//                                ob.wait();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                }
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            public void run() {
//
//                synchronized(ob) {
//                    if (contact.size() != 5) {
//                        try {
//                            t2Started = true;
//                            ob.notify();
//                            ob.wait();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("thread2 ");
//                        ob.notify();
//                    }
//                }
//            }
//        });
//        thread2.start(); thread1.start();






//       new Thread(new Runnable() {
//            public void run() {
//                synchronized(ob) {
//                    for (int i = 0; i < 10; i++) {
//                        contact.add(new Object());
//                        System.out.println("thread1 add " + contact.size() + "th element");
//                        try {
//                            Thread.sleep(100);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        if (contact.size() == 5) {
//                            ob.notify();
//                            try {
//                                ob.wait();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                }
//            }
//        },"th1").start();
//         new Thread(new Runnable() {
//            public void run() {
//                synchronized(ob) {
//                    if (contact.size() != 5) {
//                        try {
//                            ob.wait();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("thread2 ");
//                        ob.notify();
//                    }
//                }
//            }
//        },"th2").start();


    }
}