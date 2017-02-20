package routing;

import java.util.ArrayList;
import java.util.List;

import acmeroutingco.*;

public class RoutingService {
	public void route(Cargo cargo) {
		String origin = cargo.getOrigin();
		String destination = cargo.getDestination();

		String route = AcmeRoutingService.getRoute(origin, destination);
		String[] locations = route.split(",");

		List<Leg> legs = new ArrayList<Leg>();
		for (int i = 1; i < locations.length; i++) {
			legs.add(new Leg(locations[i - 1], locations[i]));
		}

		cargo.setItinerary(new Itinerary(legs));
	}

	public void reroute(Cargo cargo, String reroutePoint, String newDestination) {
		if (!cargo.getItinerary().goesThrough(reroutePoint))
			throw new IllegalArgumentException();

		Cargo tempCargo = new Cargo();
		tempCargo.setOrigin(reroutePoint);
		tempCargo.setDestination(newDestination);
		route(tempCargo);
		List<Leg> appendedLegs = tempCargo.getItinerary().legs();

		Itinerary rerouted = cargo.getItinerary().truncatedAt(reroutePoint)
				.extendedOn(appendedLegs);
		cargo.setItinerary(rerouted);
		cargo.setDestination(newDestination);
	}
}
