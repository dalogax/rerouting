package routing;
import java.util.ArrayList;
import java.util.List;

import acmeroutingco.*;

    public class RoutingService
    {

		public Itinerary findRoute(String origin, String destination) {
        	String route = AcmeRoutingService.getRoute(origin, destination);
        	String[] locations = route.split(",");

			List<Leg> legs = new ArrayList<Leg>();
        	for (int i = 1; i < locations.length; i++)
        	{
				legs.add(
			       new Leg(locations[i - 1], 
			       locations[i]));
        	}
        	return new Itinerary(legs);
		}
		

		public Itinerary findReroute(Itinerary initial, String reroutePoint,
				String newDestination) {
			return initial.truncatedAt(reroutePoint).continuingOn(findRoute(reroutePoint, newDestination));
		}

    	
    	@Deprecated
    	public void route(Cargo cargo)
        {
            Itinerary itinerary = findRoute(cargo.getOrigin(), cargo.getDestination());
			cargo.setItinerary(itinerary);
        }

    	@Deprecated 
        public void reroute(Cargo cargo, String reroutePoint, String newDestination)
        {
            Itinerary rerouted = findReroute(cargo.getItinerary(), reroutePoint, newDestination);
            cargo.setItinerary(rerouted);
            cargo.setDestination(newDestination);
        }

    }
