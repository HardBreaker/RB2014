package aufgabe_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimRace {
    
	public static final int numberOfLaps=20;
	public static final int numberOfCars = 6;
	public static final String[] names = {"Samsung", "LG", "Apple",
		"Nokia","Huawei","Sony"};
	boolean isfinished = true;
	String showedByCrash = "A crash occurred: " +
			"\n"+ names[0];//Occurred
	
	List<Car> racecars;
	int acsidtime;
	public SimRace() throws InterruptedException {
      
      racecars = new ArrayList<>();
      for(int i = 0;i<numberOfCars;i++){
    	  Car car= new Car();
    	  car.setName(names[i]);
    	  racecars.add(car);	  
      }
      doRace();
	}
	
	private void doRace() throws InterruptedException{
		for(Car car: racecars){
			car.start();
		}
		Accident ac = new Accident(racecars, this);
		acsidtime = ac.waiting;
		ac.start();
			
	}
	
	
	public String showResult() {
        StringBuffer sb = new StringBuffer();
        if(this.isfinished){
        Collections.sort(this.racecars);
        sb.append("EndStand: ");
        for(int i= 0; i<racecars.size();i++){
        	Car car = racecars.get(i);
        	sb.append("\n"+ (i+1)+ ". Platz: " + car.toString()+ " Zeit: " + car.time);
        }}
        else{
        	sb = showbyCrash();
        }
		return sb.toString();
	}
	
	private StringBuffer showbyCrash(){
        StringBuffer sb = new StringBuffer();
        
        sb.append("Race stopped! A crash occurred after: " +acsidtime +" ms");
        for(Car car: racecars){
        	sb.append("\n" + car.toString()+ " -. Platz "+ " Zeit: "+ "--:--");
        }
    			
      return sb;
	}
}
