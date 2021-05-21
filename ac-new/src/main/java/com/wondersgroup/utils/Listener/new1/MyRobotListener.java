package com.wondersgroup.utils.Listener.new1;

public class MyRobotListener implements RobotListener {

	@Override
	public void working(Even even) {
		
		Robot robot = even.getRobot();
		System.out.println("提示111");
		
	}

	@Override
	public void dancing(Even even) {
		
		Robot robot = even.getRobot();
		System.out.println("提示222");
	}

}
