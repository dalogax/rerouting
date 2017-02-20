package routing;

import static org.junit.Assert.*;

import org.junit.Test;
    
    public class TestRouting
    {
        @Test
        public void initialRoutingBackwardCompatibility()
        {
 	        RoutingService service = new RoutingService();

 	        Itinerary expected = new Itinerary(
    	       new Leg("HKG","LGB"),
    	       new Leg("LGB","DAL"));

    	    //Side-effect operation still works, although detailed testing would focus on 
    	    //the function, because the most complex calculations have been isolated there.
            Cargo cargo = new Cargo();
            cargo.setOrigin ("HKG");
            cargo.setDestination ("DAL");
            service.route(cargo);
            assertEquals(expected, cargo.getItinerary());
        }

        @Test
	    public void initialRouting2Legs()
        {
    		Itinerary expected = new Itinerary(
    		   new Leg("HKG","LGB"),
    		   new Leg("LGB","DAL"));

            RoutingService service = new RoutingService();
            assertEquals(expected, service.findRoute("HKG", "DAL"));
        }

        @Test
        public void reroutingBackwardCompatibility()
        {
    	    //Side-effect operation still works, although detailed testing would focus on function.
            Cargo cargo = new Cargo();
            cargo.setOrigin ("HKG");
            cargo.setDestination ("DAL");
            cargo.setItinerary(new Itinerary(
               new Leg("HKG","LGB"),
               new Leg("LGB","DAL")));
            
            RoutingService service = new RoutingService();
            service.reroute(cargo, "LGB", "SEA");
            
            //Post condition: itinerary is preserved up to reroutePoint
            Leg expectedFirstLeg = new Leg("HKG", "LGB");
            assertEquals(expectedFirstLeg, cargo.getItinerary().legs().get(0));

            //Post condition: itinerary is connected
            assertTrue(cargo.getItinerary().isConnected());

            //Post condition: itinerary ends at new destination
            assertEquals("SEA", cargo.getItinerary().lastLeg().end());

            //Post condition: cargo destination is newDestination
            assertEquals("SEA", cargo.getDestination());

        }

        @Test
        public void rerouting()
        {
            Itinerary initial = new Itinerary(
               new Leg("HKG","LGB"),
               new Leg("LGB","DAL"));
            
            RoutingService service = new RoutingService();
            Itinerary rerouted = service.findReroute(initial, "LGB", "SEA");
            
            //itinerary is preserved up to reroutePoint
            Leg expectedFirstLeg = new Leg("HKG", "LGB");
            assertEquals(expectedFirstLeg, rerouted.legs().get(0));

            //itinerary ends at new destination
            assertEquals("SEA", rerouted.end());

            //itinerary is connected
            assertTrue(rerouted.isConnected());

        }

    }
