package simulator.factories;

import org.json.JSONObject;
import simulator.model.DequeuingStrategy;
import simulator.model.MoveAllStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy>{
    MoveAllStrategyBuilder() {
        super("move_all_dqs");
    }

    @Override
    protected DequeuingStrategy createTheInstance(JSONObject data) {
        DequeuingStrategy o = null;
        o = new MoveAllStrategy();
        return o;
    }

    protected DequeuingStrategy createTheInstance() {
            DequeuingStrategy o = null;
            o = new MoveAllStrategy();
            return o;
        }
}
