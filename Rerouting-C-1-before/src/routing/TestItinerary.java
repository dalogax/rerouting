package routing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestItinerary {
	
	@Test
	public void truncateAt() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		itin.add(new Leg("LGB", "DAL"));

		assertEquals(2, itin.getLegs().size());
		itin.truncateAt("LGB");
		assertEquals(1, itin.getLegs().size());

		assertEquals("HKG", itin.getLegs().get(0).start());
		assertEquals("LGB", itin.getLegs().get(0).end());
	}

	@Test
	public void add()
	// This method already existed, but was untested.
	{
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));

		assertEquals(1, itin.getLegs().size());
		itin.add(new Leg("LGB", "SEA"));
		assertEquals(2, itin.getLegs().size());

		assertEquals("HKG", itin.getLegs().get(0).start());
		assertEquals("LGB", itin.getLegs().get(0).end());
		assertEquals("LGB", itin.getLegs().get(1).start());
		assertEquals("SEA", itin.getLegs().get(1).end());
	}

	@Test
	public void extendOn() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		List<Leg> legs = new ArrayList<Leg>();
		legs.add(new Leg("LGB", "DAL"));
		legs.add(new Leg("DAL", "CHI"));
		itin.extendOn(legs);
		assertEquals(3, itin.getLegs().size());
		assertEquals("HKG", itin.getLegs().get(0).start());
		assertEquals("LGB", itin.getLegs().get(0).end());
		assertEquals("LGB", itin.getLegs().get(1).start());
		assertEquals("DAL", itin.getLegs().get(1).end());
		assertEquals("DAL", itin.getLegs().get(2).start());
		assertEquals("CHI", itin.getLegs().get(2).end());
	}

	@Test
	public void isConnected() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		itin.add(new Leg("LGB", "SEA"));
		assertTrue(itin.isConnected());

		Itinerary itin2 = new Itinerary();
		itin2.add(new Leg("HKG", "LGB"));
		itin2.add(new Leg("SEA", "DAL"));
		assertFalse(itin2.isConnected());
	}

	@Test
	public void goesThrough() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		itin.add(new Leg("LGB", "DAL"));
		assertTrue(itin.goesThrough("HKG"));
		assertTrue(itin.goesThrough("LGB"));
		assertTrue(itin.goesThrough("DAL"));
		assertFalse(itin.goesThrough("SEA"));
	}

	@Test
	public void start() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		itin.add(new Leg("LGB", "DAL"));
		itin.add(new Leg("DAL", "CHI"));
		assertEquals("HKG", itin.start());
	}

	@Test
	public void end() {
		Itinerary itin = new Itinerary();
		itin.add(new Leg("HKG", "LGB"));
		itin.add(new Leg("LGB", "DAL"));
		itin.add(new Leg("DAL", "CHI"));
		assertEquals("CHI", itin.end());
	}

}
