package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy>{
    public MostCrowdedStrategyBuilder() {
        super("most_crowded_lss");
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
    	if(data.isEmpty()) {
        	throw new IllegalArgumentException("Data is empty");
        }
        LightSwitchingStrategy o = null;
        if(data.has("timeslot")){
            o = new MostCrowdedStrategy(data.getInt("timeslot"));
        }else  o = new MostCrowdedStrategy(1);
        
        return o;
    }

}
