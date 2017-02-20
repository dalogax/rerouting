
package routing;

import static org.junit.Assert.*;

import org.junit.Test;
    
    public class TestRouting
    {
        @Test
	    public void initialRouting2Legs()
        {
            Cargo cargo = new Cargo();
            cargo.setOrigin ("HKG");
            cargo.setDestination ("DAL");
            RoutingService service = new RoutingService();
            service.route(cargo);
            assertEquals(2, cargo.getItinerary().getLegs().size());
            assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
			assertEquals("LGB", cargo.getItinerary().getLegs().get(0).getEnd());
			assertEquals("LGB", cargo.getItinerary().getLegs().get(1).getStart());
			assertEquals("DAL", cargo.getItinerary().getLegs().get(1).getEnd());
        }

        @Test
        public void initialRouting1Leg()
        {
            Cargo cargo = new Cargo();
            cargo.setOrigin ("HKG");
            cargo.setDestination ("SEA");
            RoutingService service = new RoutingService();
            service.route(cargo);
            assertEquals(1, cargo.getItinerary().getLegs().size());
            assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
            assertEquals("SEA", cargo.getItinerary().getLegs().get(0).getEnd());
        }

        @Test
        public void rerouting()
        {
            Cargo cargo = new Cargo();
            cargo.setOrigin ("HKG");
            cargo.setDestination ("DAL");
            RoutingService service = new RoutingService();
            service.route(cargo);
            cargo.setDestination ("SEA");
            service.reroute(cargo, "LGB");
            assertEquals(2, cargo.getItinerary().getLegs().size());
            assertEquals("HKG", cargo.getItinerary().getLegs().get(0).getStart());
            assertEquals("LGB", cargo.getItinerary().getLegs().get(0).getEnd());
            assertEquals("LGB", cargo.getItinerary().getLegs().get(1).getStart());
            assertEquals("SEA", cargo.getItinerary().getLegs().get(1).getEnd());
        }
    }
