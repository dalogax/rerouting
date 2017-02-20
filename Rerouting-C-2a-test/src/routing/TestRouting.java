package routing;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRouting {
	
	@Test
	public void initialRouting2getLegs() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("DAL");
		RoutingService service = new RoutingService();
		service.route(cargo);
		assertEquals(2, cargo.getItinerary().getLegs().size());
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).start());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(0).end());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(1).start());
		assertEquals("DAL", cargo.getItinerary().getLegs().get(1).end());
	}

	@Test
	public void initialRouting1Leg() {
		Cargo cargo = new Cargo();
		cargo.setOrigin("HKG");
		cargo.setDestination("SEA");
		RoutingService service = new RoutingService();
		service.route(cargo);
		assertEquals(1, cargo.getItinerary().getLegs().size());
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).start());
		assertEquals("SEA", cargo.getItinerary().getLegs().get(0).end());
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
		service.reroute(cargo, "LGB", "SEA"); // New parameter makes
												// reroutePoint explicit.

		// Post condition: itinerary is preserved up to reroutePoint
		assertEquals("HKG", cargo.getItinerary().getLegs().get(0).start());
		assertEquals("LGB", cargo.getItinerary().getLegs().get(0).gnd());

		// Post condition: itinerary ends at new destination
		assertEquals("SEA", cargo.getItinerary().end());

		// Post condition: cargo destination is newDestination
		assertEquals("SEA", cargo.getDestination());

		// Post condition: itinerary is connected
		assertTrue(cargo.getItinerary().isConnected());

	}
}
