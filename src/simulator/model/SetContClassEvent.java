package simulator.model;

import simulator.misc.Pair;

import java.util.List;

public class SetContClassEvent extends Event {
    private List<Pair<String, Integer>> pairs;

    public SetContClassEvent(int i, List<Pair<String, Integer>> pairs) {
        super(i);
        this.pairs = pairs;
    }

    @Override
    void execute(RoadMap map) {
        String s;
        Integer w;
        for(int i = 0;i<pairs.size();i++){
            s = pairs.get(i).getFirst();
            w = pairs.get(i).getSecond();
            map.getVehicle(s).setContaminationClass(w);
        }

    }
}
