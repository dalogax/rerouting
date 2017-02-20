package routing;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRouting {
	
	@Test
	public void initialRouting2Legs() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("DAL");
		RoutingService service = new RoutingService();
		service.route(cargo);
		assertEquals(2, cargo.getItinerary().getLegs().size());
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(0).getEnd());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(1).getStart());
		assertEquals("DAL", cargo.getItinerary().getLegs().get(1).getEnd());
	}

	@Test
	public void initialRouting1Leg() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("SEA");
		RoutingService service = new RoutingService();
		service.route(cargo);
		assertEquals(1, cargo.getItinerary().getLegs().size());
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
		assertEquals("SEA", cargo.getItinerary().getLegs().get(0).getEnd());
	}

	@Test
	public void rerouting() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("DAL");
		cargo.getItinerary().add(new Leg("HKG", "LGB"));
		cargo.getItinerary().add(new Leg("LGB", "DAL"));

		RoutingService service = new RoutingService();
		service.reroute(cargo, "LGB", "SEA");

		// Post condition: cargo destination is newDestination
		assertEquals("SEA", cargo.getDestination());

		// Post condition: itinerary is preserved up to reroutePoint
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(0).getEnd());

		// Post condition: itinerary ends at new destination
		int itinLength = cargo.getItinerary().getLegs().size();
		assertEquals("SEA", cargo.getItinerary().getLegs().get(itinLength - 1)
				.getEnd());

		// Post condition: itinerary is connected
		List<Leg> legs = cargo.getItinerary().getLegs();
		boolean isConnected = true;
		if (legs.size() >= 2) {
			for (int i = 1; i < legs.size(); i++) {
				Leg left = legs.get(i - 1);
				Leg right = legs.get(i);
				if (!left.getEnd().equals(right.getStart()))
					isConnected = false;
			}
		}
		assertTrue(isConnected);
	}
}
