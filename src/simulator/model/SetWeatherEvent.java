package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{
	private List<Pair<String,Weather>> ws;
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		if(ws != null) {
			this.ws = ws;
		}
		else
			throw new IllegalArgumentException("Weather argument can`t be null");
	}
	@Override
	void execute(RoadMap map) {
		String s;
		Weather w;
		Road r;
		for(int i = 0;i<ws.size();i++){
			s = ws.get(i).getFirst();
			w = ws.get(i).getSecond();
			r = map.getRoad(s);
			if(r != null) {
				r.setWeather(w);
			}
			else 
				throw new IllegalArgumentException("Road not found");
				
		}

	}

}
