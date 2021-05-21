package com.wondersgroup.utils.Listener.new1;

public class Robot {

	private  RobotListener listener;
	
	public void registerListener(RobotListener listener){
		      this.listener  = listener;
		     }
	
	 public void working(){
		          if(listener!=null){
		             Even even = new Even(this);
		             this.listener.working(even);
		        }
		          System.out.println("执行111");
		   }
	
	 public void dancing(){
         if(listener!=null){
            Even even = new Even(this);
            this.listener.dancing(even);
       }
         System.out.println("执行222");
  }
	
}
