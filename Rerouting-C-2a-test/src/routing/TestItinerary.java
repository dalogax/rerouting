package routing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestItinerary {
	
	@Test
	public void truncatedAt() {
		Itinerary original = 
				new Itinerary(
						new Leg("HKG", "LGB"), 
						new Leg("LGB", "DAL"));

		assertEquals(2, original.getLegs().size());
		Itinerary truncated = original.truncatedAt("LGB");

		assertEquals(1, truncated.getLegs().size());
		assertEquals("HKG", truncated.getLegs().get(0).start());
		assertEquals("LGB", truncated.getLegs().get(0).end());

		// Assert that original itinerary has not mutated
		assertEquals(2, original.getLegs().size());
		assertEquals("HKG", original.getLegs().get(0).start());
		assertEquals("LGB", original.getLegs().get(0).end());
		assertEquals("LGB", original.getLegs().get(1).start());
		assertEquals("DAL", original.getLegs().get(1).end());
	}

	// Deleted test for mutator add(). Method will be deleted.

	@Test
	public void extendedOn() {
		Itinerary original = new Itinerary(new Leg("HKG", "LGB"));
		List<Leg> legs = new ArrayList<Leg>();
		legs.add(new Leg("LGB", "DAL"));
		legs.add(new Leg("DAL", "CHI"));

		Itinerary extended = original.extendedOn(legs);
		assertEquals(3, extended.getLegs().size());
		assertEquals("HKG", extended.getLegs().get(0).start());
		assertEquals("LGB", extended.getLegs().get(0).end());
		assertEquals("LGB", extended.getLegs().get(1).start());
		assertEquals("DAL", extended.getLegs().get(1).end());
		assertEquals("DAL", extended.getLegs().get(2).start());
		assertEquals("CHI", extended.getLegs().get(2).end());

		// Assert that original has not mutated
		assertEquals(1, original.getLegs().size());
		assertEquals("HKG", original.getLegs().get(0).start());
		assertEquals("LGB", original.getLegs().get(0).end());
	}

	@Test
	public void isConnected() {
		Itinerary itin = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB","SEA"));
		assertTrue(itin.isConnected());

		Itinerary itin2 = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("SEA","DAL"));
		assertFalse(itin2.isConnected());
	}

	@Test
	public void goesThrough() {
		Itinerary itin = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB", "DAL"));
		assertTrue(itin.goesThrough("HKG"));
		assertTrue(itin.goesThrough("LGB"));
		assertTrue(itin.goesThrough("DAL"));
		assertFalse(itin.goesThrough("SEA"));
	}

	@Test
	public void start() {
		Itinerary itin = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB", "DAL"), 
				new Leg("DAL", "CHI"));
		assertEquals("HKG", itin.start());
	}

	@Test
	public void end() {
		Itinerary itin = new Itinerary(
				new Leg("HKG", "LGB"), 
				new Leg("LGB", "DAL"), 
				new Leg("DAL", "CHI"));
		assertEquals("CHI", itin.end());
	}

}
