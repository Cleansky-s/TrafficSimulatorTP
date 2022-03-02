package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends Builder<Event>{

	public NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		NewInterCityRoadEvent n = null;
		int time = data.getInt("time");
		String id = data.getString("id");
		String srcJunc = data.getString("src");
		String destJunc = data.getString("dest"); 
		int length = data.getInt("length");
		int co2Limit = data.getInt("co2limit");
		int maxSpeed = data.getInt("maxspeed");
		String w = data.getString("weather");
		Weather weather = Weather.valueOf(w);
		
		
		n = new NewInterCityRoadEvent(time, id, srcJunc, destJunc, length, co2Limit, maxSpeed, weather);
		return n;
		
	}

}
