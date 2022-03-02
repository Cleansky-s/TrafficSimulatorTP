package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event>{

	SetContClassEventBuilder() {
		super("set_cont_class");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		SetContClassEvent n = null;
		int time = data.getInt("time");
		List<Pair<String, Integer>> pairs = new ArrayList<Pair<String, Integer>>();
		JSONArray o = new JSONArray();
		o = data.getJSONArray("info");
		for(int i = 0; i < o.length(); i++) {
			Pair<String, Integer> e = new Pair<String, Integer>(o.getJSONObject(i).getString("vehicle"),
					o.getJSONObject(i).getInt("class"));
			pairs.add(e);
		}
		n = new SetContClassEvent(time, pairs);
		return n;
	}

}
