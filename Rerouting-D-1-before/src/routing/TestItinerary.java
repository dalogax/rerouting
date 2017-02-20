package routing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestItinerary {
		
    @Test
    public void truncatedAt()
    {
    	//A value object must be created whole.
    	Itinerary itin = new Itinerary(
           new Leg("HKG","LGB"),
           new Leg("LGB","DAL"));

        Itinerary expectedTruncated = new Itinerary(
           new Leg("HKG","LGB"));

        assertEquals(expectedTruncated, itin.truncatedAt("LGB"));

        //Note: truncatedAt is not just a rename of truncateAt.
        // it is a side-effect-free function.
        Itinerary expectedUnmodified = new Itinerary(
                new Leg("HKG","LGB"),
                new Leg("LGB","DAL"));
        
        assertEquals(expectedUnmodified, itin);       
    }
    

    @Test
    public void extendedOn()
    {
        Itinerary itin = new Itinerary(
        		new Leg("HKG","LGB"));

   		Itinerary expectedExtended = new Itinerary(
     		   new Leg("HKG","LGB"),
     		   new Leg("LGB","DAL"),
    		   new Leg("DAL","CHI"));
   		
   		List<Leg> newLegs = new ArrayList<Leg>();
   		newLegs.add(new Leg("LGB", "DAL"));
   		newLegs.add(new Leg("DAL", "CHI"));
   		assertEquals(expectedExtended, itin.extendedOn(newLegs));
   		
        //Note: extendedOn is not just a rename of extendOn.
        // it is a side-effect-free function.
        Itinerary expectedUnmodified = new Itinerary(
                new Leg("HKG","LGB"));
        
        assertEquals(expectedUnmodified, itin);       
    }

    @Test
    public void isConnected()
    {
        Itinerary itin = new Itinerary(
               new Leg("HKG","LGB"),
          	   new Leg("LGB","SEA"));
             assertTrue(itin.isConnected());

             Itinerary itin2 = new Itinerary(
                new Leg("HKG","LGB"),
                new Leg("SEA","DAL"));
             assertFalse(itin2.isConnected());
    }

    @Test
    public void goesThrough()
    {
        Itinerary itin = new Itinerary(
           new Leg("HKG","LGB"),
           new Leg("LGB","DAL"));
        assertTrue(itin.goesThrough("HKG"));
        assertTrue(itin.goesThrough("LGB"));
        assertTrue(itin.goesThrough("DAL"));
        assertFalse(itin.goesThrough("SEA"));
   }
  
    @Test
    public void start()
    {
        Itinerary itin = new Itinerary(
           new Leg("HKG","LGB"),
           new Leg("LGB","DAL"),
           new Leg("DAL","CHI"));
        assertEquals("HKG", itin.start());
    }

    @Test
    public void end()
    {
        Itinerary itin = new Itinerary(
                new Leg("HKG","LGB"),
                new Leg("LGB","DAL"),
                new Leg("DAL","CHI"));
		assertEquals("CHI", itin.end());
    }
}
