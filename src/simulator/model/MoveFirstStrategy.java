package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> res = new ArrayList<Vehicle>();
		if(q.size() > 0) {
			res.add(q.get(0));
		}
		return res;
	}

}
