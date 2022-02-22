package simulator.model;

import simulator.misc.Pair;

import java.util.List;

public class SetContClassEvent extends Event {
    public SetContClassEvent(int i, List<Pair<String, Integer>> pairs) {
        super(i);
    }

    @Override
    void execute(RoadMap map) {

    }
}
