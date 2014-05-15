package aufgabe_02;

public class Start {
   
   
   public static void main(String[] args) throws InterruptedException {
	   

		SimRace race =new SimRace();
		for(Car car: race.racecars){
			car.join();
		}
		System.err.println(race.showResult());
	
		
	
   }
   
}
