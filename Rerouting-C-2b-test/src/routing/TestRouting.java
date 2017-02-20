package routing;

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

		Itinerary expected = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB", "DAL"));

		assertEquals(expected, cargo.getItinerary());
	}

	@Test
	public void initialRouting1Leg() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("SEA");
		RoutingService service = new RoutingService();
		service.route(cargo);

		Itinerary expected = new Itinerary(new Leg("HKG", "SEA"));

		assertEquals(expected, cargo.getItinerary());
	}

	@Test
	public void rerouting() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("DAL");
		cargo.setItinerary(new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB","DAL")));

		RoutingService service = new RoutingService();
		service.reroute(cargo, "LGB", "SEA");

		// Post condition: itinerary is preserved up to reroutePoint
		assertEquals("HKG", cargo.getItinerary().legs().get(0).start());
		assertEquals("LGB", cargo.getItinerary().legs().get(0).end());

		// Post condition: itinerary ends at new destination
		assertEquals("SEA", cargo.getItinerary().end());

		// Post condition: cargo destination is newDestination
		assertEquals("SEA", cargo.getDestination());

		// Post condition: itinerary is connected
		assertTrue(cargo.getItinerary().isConnected());

	}
}
