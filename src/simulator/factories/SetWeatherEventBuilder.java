package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event>{

	public SetWeatherEventBuilder() {
		super("set_weather");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		if(data.isEmpty()) {
        	throw new IllegalArgumentException("Data is empty");
        }
		SetWeatherEvent n = null;
		int time = data.getInt("time");
		JSONArray o = new JSONArray();
		o = data.getJSONArray("info");
		List<Pair<String,Weather>> ws = new ArrayList<Pair<String, Weather>>();
		for(int i =0;i<o.length();i++) {
			Pair<String, Weather> p = new Pair<String, Weather>(o.getJSONObject(i).getString("road"), Weather.valueOf(o.getJSONObject(i).getString("weather")));
			ws.add(p);
		}
		n = new SetWeatherEvent(time, ws);
		return n;
	}

	

}
