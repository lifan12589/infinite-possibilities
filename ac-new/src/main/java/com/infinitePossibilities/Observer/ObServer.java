package com.infinitePossibilities.Observer;

/**
 * 观察者
 */
public abstract class ObServer {

    protected String name;
    protected Subjects subject;

        public ObServer(String name, Subjects subject) {
          this.name = name;
          this.subject = subject;

        }

        public abstract void update();


}
