package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{
		private List<Pair<String,Weather>> ws;
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		this.ws = ws;
		}

	@Override
	void execute(RoadMap map) {
		String s;
		Weather w;
		for(int i = 0;i<ws.size();i++){
			s = ws.get(i).getFirst();
			w = ws.get(i).getSecond();
			map.getRoad(s).setWeather(w);
		}

	}

}
