package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public class NewInterCityRoadEventBuilder extends Builder<Event>{

	NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		return null;
	}

}
