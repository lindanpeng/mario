package com.test;

public class HelloWorld {
public static void main(String[] args) throws InterruptedException {
	long startTime=System.currentTimeMillis();
	Thread.sleep(1000);
	long endTime=System.currentTimeMillis();
	System.out.println(endTime-startTime);
}
}
