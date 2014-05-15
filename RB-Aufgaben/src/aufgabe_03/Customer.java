package aufgabe_03;

/*
 * Customer.java
 * Version 1.0
 * Autor: M. Hübner
 * Zweck: Simuliert das Verhalten eines Shop-Kunden
 */

public class Customer extends Thread {
	private Shop currentShop;

	public Customer(Shop s) {
		currentShop = s;
	}

	public void run() {

		while (!isInterrupted()) {

			// Versuche, in das Geschäft einzutreten
			System.err.println(this.getName() + " wants to enter the shop!");
			currentShop.enter();

			if (!isInterrupted()) {
				// Für unbestimmte Zeit schlafen
				enjoyLife();
			}

		}
	}

	// Customer benutzen diese Methode, um sich zu vergnügen
	public void enjoyLife() {
		int sleepTime = (int) (1000 * Math.random());

		try {
			// Thread blockieren
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags für den eigenen Thread
			this.interrupt();
		}
	}
}
