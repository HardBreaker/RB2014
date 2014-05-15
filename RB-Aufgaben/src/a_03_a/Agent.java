package a_03_a;


public class Agent extends Thread{

	
	private Table table;
		
	public Agent(Table table){
		this.table =table;
	}
		
	public void run(){
		while(!isInterrupted()){
			try {
				table.putIngredients();
			} catch (InterruptedException e) {
				this.interrupt();
			}	
		}
	}
}
