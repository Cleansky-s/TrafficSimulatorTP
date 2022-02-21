package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy{
	
	private int times;
	public MostCrowdedStrategy(int time) {
	this.times = time;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		if(roads == null) {
			return -1;
		}
		else if(currGreen == -1) {
			int ml = 0;
			for(int i = 0;i<qs.size();i++) {
				if(ml < qs.get(i).size())
				ml = qs.get(i).size();
			}
			
			return ml;
		}
		else if(currTime-lastSwitchingTime < times) {
			return currGreen;
		}
		else {
			int mk = 0;
			for(int i = currGreen+1;i<qs.size();i++) {
				if(mk < qs.get(i).size())
				mk = qs.get(i).size();
			}
			return mk;
		}
	}

}
