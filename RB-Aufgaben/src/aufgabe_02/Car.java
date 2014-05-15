package aufgabe_02;

public class Car extends Thread implements Comparable<Car> {

    int time;

    boolean finished = false;
	public Car() {
		this.time =0;
		;
	}
     @Override
    public void run() {
        for(int i=0;i<SimRace.numberOfLaps;i++){   	
        	 try {
        		 int time =(int)(Math.random()*100);
        		 this.time += time;
				 Thread.sleep(time);
			} catch (InterruptedException e) {
				interrupt();
			} 	
         }
        finished=true;
    }
	@Override
	public String toString() {
		return "["+getName()+"]" ;
	}
	@Override
	public int compareTo(Car o){
	return Integer.valueOf(this.time).compareTo(o.time);
	}
	
	
}
