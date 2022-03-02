package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event>{
	
	private Factory<LightSwitchingStrategy> lssFactory;
	private Factory<DequeuingStrategy> dqsFactory;
    public NewJunctionEventBuilder(Factory<LightSwitchingStrategy>
    lssFactory, Factory<DequeuingStrategy> dqsFactory) {
    	super("new_junction");
    	this.lssFactory = lssFactory;
    	this.dqsFactory = dqsFactory;
    }

    
    
    protected Event createTheInstance(JSONObject data) {
    	NewJunctionEvent n = null;
    	String id;
        int coox,cooy,time;
        LightSwitchingStrategy lsStrat;
        DequeuingStrategy dqStrat;
        id = data.getString("id");
        coox = data.getJSONArray("coor").getInt(0);
        cooy = data.getJSONArray("coor").getInt(1);
        time = data.getInt("time");
        lsStrat = lssFactory.createInstance(data.getJSONObject("ls_strategy"));
        dqStrat = dqsFactory.createInstance(data.getJSONObject("dq_strategy"));
        n = new NewJunctionEvent(time, id, lsStrat, dqStrat, coox, cooy);
        return n;
    }

}
