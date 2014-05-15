package a_03_a;

import java.util.ArrayList;
import java.util.List;

public class Table {

	enum Ingredient {
		TOBACCO, PAPER, MATCHES;
		public String toString() {
			String string = "MATCHES";
			if (this == TOBACCO) {
				string = "TOBACCO";
			} else if (this == PAPER)
				string = "PAPER";
			return string;
		};

	}

	private static final int smokeTime = 1000;
	Ingredient firstIng;
	Ingredient secondIng;
	boolean isEmpty = true;

	public synchronized void putIngredients() throws InterruptedException {
		while (!isEmpty) {
			this.wait();
		}
		
		System.err.println("-------- Eine neue Runde beginnt: ---------");
		
		int[] randomnumbers = randomNumbs();

		this.firstIng = Ingredient.values()[randomnumbers[0]];
		this.secondIng = Ingredient.values()[randomnumbers[1]];

		isEmpty = false;
		notifyAll();
		
		System.err.println(firstIng.toString()
				+ " wurde vom Agenten auf den Tisch gelegt");
		System.err.println(secondIng.toString()
				+ " wurde vom Agenten auf den Tisch gelegt");
	}

	public synchronized void smoke(Smoker smoker) throws InterruptedException {

		if (smoker.ingredient != firstIng && smoker.ingredient != secondIng
				&& !isEmpty) {
			System.err.println(Thread.currentThread().getName()
					+ " raucht die Zigarette");

			Thread.sleep(smokeTime);

			this.isEmpty = true;
			notifyAll();
		} else {

			this.wait();

		}

	}

	private int[] randomNumbs() {
		int[] randoms = new int[2];
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++)
			numbers.add(i);
		randoms[0] = numbers.remove((int) (Math.random() * numbers.size()));
		randoms[1] = numbers.remove((int) (Math.random() * numbers.size()));
		return randoms;
	}
}
