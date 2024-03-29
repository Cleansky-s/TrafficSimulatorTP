package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	private TrafficSimulator ts;
	private Factory<Event> eventFactory;
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory)
	{
		if(sim != null && eventsFactory != null) {
			this.eventFactory = eventsFactory;
			this.ts = sim;
		}
		else throw new IllegalArgumentException("Controller arguments can�t be null");
	}
	public void loadEvents(InputStream in) {
		JSONObject jo = new JSONObject(new JSONTokener(in));
		Event v;
		JSONArray eventArray = jo.getJSONArray("events");
		for(int i = 0; i < eventArray.length(); i++) {
			v = eventFactory.createInstance(eventArray.getJSONObject(i));
			ts.addEvent(v);
			//Este m�todo debe lanzar una excepci�n si la entrada JSON no encaja con la de arriba.
		}
	}
	
	public void run(int n, OutputStream out) {
		PrintStream p = new PrintStream(out);
		JSONObject o = new JSONObject();
		p.println("{\n" + 
				"  \"states\": [");
		for(int i = 0; i < n; i++) {
			ts.advance();
			o.append("states", ts.report());
			p.print(o.getJSONArray("states").get(i).toString());
			if(i != (n-1)) {
				p.println(",");
			}
			else p.println();
		}
		p.println("]\n" + 
				"}");
	}
	
	public void reset() {
		ts.reset();
	}
}
