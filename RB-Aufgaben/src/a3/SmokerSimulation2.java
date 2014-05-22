package a3;

import java.util.Random;

public class SmokerSimulation {
	public static long smokeTime = 1000;
	public static long gameTime = 10000;
	
	public static void main(String[] args){
		simulate();
	}
	
	public static void simulate(){
		System.err.println("Initializing Objects!");
		Agent agent = new Agent();
		Smoker s1 = new Smoker(agent,0);
		Smoker s2 = new Smoker(agent,1);
		Smoker s3 = new Smoker(agent,2);
		System.err.println("Starting Threads!");
		agent.start();
		s1.start();
		s2.start();
		s3.start();
		try {
			System.err.println("Starting timer!");
			Thread.sleep(gameTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("Interrupting threads!");
		agent.interrupt();
		s1.interrupt();
		s2.interrupt();
		s3.interrupt();
	}
	
	private static class Smoker extends Thread{
		//0: tobacco
		//1: paper
		//2: matches
		int indexOfMyIngredient;
		Agent agent;
		
		Smoker(Agent agent, int ingredient){
			this.agent = agent;
			indexOfMyIngredient = ingredient;
		}
		public void run(){
			while(!this.isInterrupted()){
				int missingIngredient = agent.getIndexOfMissingIngredient();
				if(missingIngredient == indexOfMyIngredient){
					try {
						System.err.println("Missing Ingredient: "+missingIngredient);
						System.err.println("Got missing ingredient ("+indexOfMyIngredient+"), Smoking...");
						Thread.sleep(smokeTime);
						System.err.println("Done smoking.");
						agent.gameOver();
					} catch (InterruptedException e) {
						System.err.println("Smoker was interrupted while smoking.");
						interrupt();
					}
				}
			}
			if(this.isInterrupted())
				System.err.println("Smoker was interrupted.");
		}
		
	}
	
	private static class Agent extends Thread{
		private Random random;
		private int indexOfMissingIngredient;
		
		Agent(){
			random = new Random();
		}
		
		synchronized int getIndexOfMissingIngredient(){
			return indexOfMissingIngredient;
		}
		
		private synchronized void play() throws InterruptedException{
			while(!isInterrupted()){
				dealIngredients();
				System.err.println("Ingredients dealt, waiting for smokers...");
				this.wait();
			}
		}
		
		synchronized void gameOver(){
			dealIngredients();
			this.notify();
			notifyAll();
		}

		private void dealIngredients(){
			indexOfMissingIngredient = random.nextInt(3);
//			System.err.println("Missing Ingredient: " + indexOfMissingIngredient);
		}
		
		public void run(){
			try {
				dealIngredients();
				play();
			} catch (InterruptedException e) {
				System.err.println("Agent was interrupted.");
			}
		}
		
		
	}

}
