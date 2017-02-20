package routing;

import java.util.List;

import acmeroutingco.*;

public class RoutingService {
	public void route(Cargo cargo) {
		String origin = cargo.getOrigin();
		String destination = cargo.getDestination();

		String route = AcmeRoutingService.getRoute(origin, destination);
		String[] locations = route.split(",");

		cargo.setItinerary(new Itinerary());
		for (int i = 1; i < locations.length; i++) {
			cargo.getItinerary().add(new Leg(locations[i - 1], locations[i]));
		}
	}

	public void reroute(Cargo cargo, String reroutePoint, String newDestination) {
		if (!cargo.getItinerary().goesThrough(reroutePoint))
			throw new IllegalArgumentException();

		cargo.getItinerary().truncateAt(reroutePoint);

		Cargo tempCargo = new Cargo();
		tempCargo.setOrigin(reroutePoint);
		tempCargo.setDestination(newDestination);
		route(tempCargo);
		List<Leg> appendedLegs = tempCargo.getItinerary().getLegs();

		cargo.getItinerary().extendOn(appendedLegs);
		cargo.setDestination(newDestination);
	}
}
