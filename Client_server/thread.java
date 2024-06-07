package example;

import java.io.*;

class thread{
public static void main (String[] args) throws InterruptedException {
    final Addition a=new Addition();

    Thread t1 = new Thread(new Runnable()
    {
        public void run(){
            try {
               a.add(1,10); 
            } catch(Exception e) {
            }
        }
    });

    Thread t2 = new Thread(new Runnable()
    {
        public void run(){
            try {
                a.add(1,4);
            } catch(Exception e) {
            }
        }
    });

    t1.start();
    t2.start();

    //t1.join();
    //t2.join();
}

public static class Addition {
	  int a, b;

	  public void add(int a, int b) throws InterruptedException {
	    int sum = 0;
	    synchronized (this) {
	      for (int i = a; i <= b; i++) {
	        sum = sum + i;
	        Thread.sleep(1000);
	      }
	      System.out.println("Sum=" + sum);
	    }
	  }
	}
}