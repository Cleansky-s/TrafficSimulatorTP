package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event>{

	public NewVehicleEventBuilder() {
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		NewVehicleEvent n = null;
		int time = data.getInt("time");
		String id = data.getString("id");
		int maxSpeed = data.getInt("maxspeed");
		int contClass = data.getInt("class");
		List<String> itinerary = new ArrayList<String>();
		JSONArray o = new JSONArray();
		o = data.getJSONArray("itinerary");
		for(int i = 0; i < o.length(); i++) {
			itinerary.add(o.getString(i));
		}
		n = new NewVehicleEvent(time, id, maxSpeed, contClass, itinerary);
		return n;
	}

}
