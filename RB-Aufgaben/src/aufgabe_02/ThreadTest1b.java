package aufgabe_02;



public class ThreadTest1b {
  /* Beispiel für das Starten von mehreren Threads */

  public static void main(String[] args) {
    /* Main: wird vom Hauptthread ausgeführt */

    /* Erzeuge Thread-Objekte (nur Java-Objekte) */
    Thread threadZahl = new Thread(new MyRunnableZahl());
    Thread threadText = new Thread(new MyRunnableText());
    System.err.println("-- Noch nichts passiert!--");

    /* Starte Threads */
    threadZahl.start();
    threadText.start();

    System.err.println("-- Hauptthread wird beendet!--");
  }
}

/* Eigene Klasse */
class MyRunnableZahl implements Runnable {
  /* Hochzählen und Zahlen ausgeben */
  public void run() {
    for (int i = 0; i < 10000; i++) {
      System.err.println(i);
    }
  }
}

/* Eigene Klasse */
class MyRunnableText implements Runnable {
  /* Intelligenten Text ausgeben */
  public void run() {
    for (int i = 0; i < 10000; i++) {
      System.err.println("------------ Ich bin auch noch da! ");
    }
  }
}
