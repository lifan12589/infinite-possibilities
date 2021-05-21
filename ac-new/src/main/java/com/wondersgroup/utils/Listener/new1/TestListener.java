package com.wondersgroup.utils.Listener.new1;

public class TestListener {
	
		public static void main(String[] args) {
			
			Robot robot = new Robot();
			robot.registerListener(new MyRobotListener());
			robot.working();
			robot.dancing();
		}
}
