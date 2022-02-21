package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy{
	
	private int time;
	public RoundRobinStrategy(int timeSlot) {
		this.time = timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		
		if(roads == null) {
			
			return -1;
		}
		else if(currGreen == -1) {
			return 0;
		}
		else if(currTime-lastSwitchingTime < time) {
			return currGreen;
		}
		return (currGreen + 1)%roads.size();
	}

}
