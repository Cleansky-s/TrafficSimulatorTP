package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public class NewCityRoadEventBuilder extends Builder<Event>{

	
	NewCityRoadEventBuilder() {
		super("new_city_road");
	}

	protected Event createTheInstance(JSONObject data) {
		return null;
		
	}
}
