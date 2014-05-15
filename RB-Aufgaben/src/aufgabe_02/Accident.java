package aufgabe_02;

import java.util.List;

public class Accident extends Thread{
	
	List<Car> cars;
	SimRace race;
	int waiting;
	
	public Accident(List<Car> cars,SimRace race) {
		this.cars = cars;
		this.race=race;
		waiting =(int)(Math.random()*100*SimRace.numberOfLaps); 
      
	}
	@Override
	public void run() {
      
      try {
		Thread.sleep(waiting);
		
			for(Car car:cars){
				if(!car.finished){
				car.interrupt();
				race.isfinished=false;
				}
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
