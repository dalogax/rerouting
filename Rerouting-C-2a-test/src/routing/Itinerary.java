package routing;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
	private List<Leg> legs = new ArrayList<Leg>();

	public void add(Leg leg) {
		legs.add(leg);
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void remove(Leg leg) {
		legs.remove(leg);
	}

	public void truncateAt(String reroutePoint) {
		legs = legs.subList(0, indexOf(reroutePoint));
	}

	private int indexOf(String reroutePoint) {
		for (Leg leg : legs) {
			if (leg.start().equals(reroutePoint))
				return legs.indexOf(leg);
			if (leg.end().equals(reroutePoint))
				return legs.indexOf(leg) + 1;
		}
		return -1;
	}

	public void extendOn(List<Leg> appendedLegs) {
		legs.addAll(appendedLegs);
	}

	public boolean isConnected() {
		if (legs.size() < 2)
			return true;
		for (int i = 1; i < legs.size(); i++) {
			Leg left = legs.get(i - 1);
			Leg right = legs.get(i);
			if (!left.end().equals(right.start()))
				return false;
		}
		return true;
	}

	public boolean goesThrough(String location) {
		return indexOf(location) != -1;
	}

	public Leg getLastLeg() {
		return legs.get(legs.size() - 1);
	}

	public String start() {
		return legs.get(0).start();
	}

	public String end() {
		return getLastLeg().end();
	}

}
