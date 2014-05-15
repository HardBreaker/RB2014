package a_03_a;

import java.util.ArrayList;
import java.util.List;

import a_03_a.Table.Ingredient;

public class TableService {
	private Table table;
	private static final int sleepingTime = 10000;

	public TableService() {
		table = new Table();
		startGame();
	}

	public static void main(String[] args) {
		new TableService();
	}

	private void startGame() {
		List<Smoker> smokers = new ArrayList<>();
		Ingredient[] ingredients = Ingredient.values();
		for (Ingredient ingredient : ingredients) {
			Smoker s = new Smoker(table, ingredient);
			smokers.add(s);
			s.start();
		}
		Agent a = new Agent(table);
		a.start();

		try {
			Thread.sleep(sleepingTime);

			for (Smoker s : smokers)
				s.interrupt();

			a.interrupt();
			System.err.println("Das Spiel ist beendet");
		} catch (InterruptedException e) {

		}
	}
}
