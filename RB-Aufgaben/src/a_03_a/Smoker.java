package a_03_a;

import a_03_a.Table.Ingredient;

public class Smoker extends Thread {
    
	private Table table;
	Ingredient ingredient;
	
	public Smoker(Table table, Ingredient ingredient){
		this.ingredient=ingredient;
		this.table = table;
		setName("Der Smoker mit der Zutat " + ingredient.toString());
	}
	
	public void run(){
		while(!isInterrupted()){
			try {
				table.smoke(this);
			} catch (InterruptedException e) {
              this.interrupt();
			}
		}
	}
	
}
