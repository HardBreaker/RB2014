package aufgabe_02;



public class ThreadTest2 {
  public static void main(String[] args) {
    MyThread2 testThread = new MyThread2();
    testThread.start();
    try {
      /* Für 2000 ms anhalten */
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // nichts
    }
    testThread.stop(); // STOP sollte nicht mehr verwendet werden!!
    System.err.println("Es wurde gestoppt!");
  }
}

class MyThread2 extends Thread {
  /* Hochzählen und Zahlen ausgeben */
  public void run() {
    int i = 0;
    while (true) {
      System.err.println(i++);
    }
  }
}
