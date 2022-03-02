package simulator.factories;

import org.json.JSONObject;
import simulator.model.DequeuingStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy>{
    public MoveFirstStrategyBuilder() {
        super("move_first_dqs");
    }


    @Override
    protected DequeuingStrategy createTheInstance(JSONObject data) {
        DequeuingStrategy o = null;
        o = new MoveFirstStrategy();
        return o;
    }

    protected DequeuingStrategy createTheInstance() {
        DequeuingStrategy o = null;
        o = new MoveFirstStrategy();
        return o;
    }
}
