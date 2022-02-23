package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class NewSetContClassEvent extends Event{
	private List<Pair<String,Integer>> cs;
	public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		if(cs != null) {
			this.cs = cs;
		}
		else
			throw new IllegalArgumentException("ContClass argument can`t be null");
	}


	@Override
	void execute(RoadMap map) {
		int contClass;
		Vehicle v;
		for(int i = 0;i<cs.size();i++){
			contClass = cs.get(i).getSecond();
			v = map.getVehicle(cs.get(i).getFirst());
			if(v!=null) {
				v.setContaminationClass(contClass);
			}
			else 
				throw new IllegalArgumentException("Vehicle not found");
		}
		
	}
}
