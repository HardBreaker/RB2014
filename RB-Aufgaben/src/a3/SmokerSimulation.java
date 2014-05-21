package a3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SmokerSimulation {
	public static long smokeTime = 100;
	public static long gameTime = 1000;
	
	public static void main(String[] args){
		simulate();
	}
	
	public static void simulate(){
		System.err.println("Initializing Objects!");
		Agent agent = new Agent();
		Smoker s1 = new Smoker(agent,1);
		Smoker s2 = new Smoker(agent,2);
		Smoker s3 = new Smoker(agent,3);
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
				if(agent.getIndexOfMissingIngredient() == indexOfMyIngredient){
					try {
						System.err.println("Smoking...");
						Thread.sleep(smokeTime);
					} catch (InterruptedException e) {
						System.out.println("Smoker was interrupted while smoking.");
						interrupt();
					}
					System.err.println("Done smoking.");
					agent.gameOver();
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
			notifyAll();
			return indexOfMissingIngredient;
		}
		
		private synchronized void play() throws InterruptedException{
			while(!isInterrupted()){
				dealIngredients();
				this.wait();
				notifyAll();
			}
		}
		
		synchronized void gameOver(){
			this.notify();
		}

		private void dealIngredients(){
			indexOfMissingIngredient = random.nextInt(3);
		}
		
		public void run(){
			try {
				play();
			} catch (InterruptedException e) {
				System.err.println("Agent was interrupted.");
			}
		}
		
		
	}

}
