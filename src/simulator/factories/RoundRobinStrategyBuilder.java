package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy>{
    public RoundRobinStrategyBuilder() {
        super("round_robin_lss");
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
    	if(data.isEmpty()) {
        	throw new IllegalArgumentException("Data is empty");
        }
        LightSwitchingStrategy o = null;
        if(data.has("timeslot")){
        o = new RoundRobinStrategy(data.getInt("timeslot"));
        }else  o = new RoundRobinStrategy(1);

        return o;
    }
}
