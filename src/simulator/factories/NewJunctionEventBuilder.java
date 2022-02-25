package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class NewJunctionEventBuilder extends Builder<Event>{
    NewJunctionEventBuilder(String type) {
        super("new_junction");
    }

    protected Event createTheInstance(JSONObject data) {
        int id,coox,cooy,time;
        id = data.getJSONObject("data").getInt("time");
        coox = data.getJSONObject("data").getJSONArray("coor").getInt(0);
        cooy = data.getJSONObject("data").getJSONArray("coor").getInt(0);
        return null;
    }

}
