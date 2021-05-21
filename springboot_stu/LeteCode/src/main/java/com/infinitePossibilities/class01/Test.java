package com.infinitePossibilities.class01;

public class Test {
	
	public static void main(String[] args) {
		int a = 6;
		int b = -1000;
		
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
//		 0000000110
//		-1111101000
//		-1111101110
//				 -1111101110
//				 -1111101000
//				  0000000110
//		-1111101110
//		 0000000110
//		-1111101000




		System.out.println(a);
		System.out.println(b);

		//010110  --22
		//100001  --33
		System.out.println(22 ^ 33);
		
		int[] arr = {3,1,100};
		
		System.out.println(arr[0]);
		System.out.println(arr[2]);


//		11
//		11
//		00
		//3已变为0
		swap(arr, 0, 0);

		for(int i = 0;i<arr.length;i++){
			System.out.println("----"+arr[i]);
		}


		System.out.println(arr[0]);
		System.out.println(arr[2]);

		
		
	}
	
	
	public static void swap (int[] arr, int i, int j) {
		// arr[0] = arr[0] ^ arr[0];
		arr[i]  = arr[i] ^ arr[j];
		arr[j]  = arr[i] ^ arr[j];
		arr[i]  = arr[i] ^ arr[j];
	}
	
	

}
