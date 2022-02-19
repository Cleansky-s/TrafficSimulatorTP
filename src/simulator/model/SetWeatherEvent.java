package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		// ...
		}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		
	}

}
