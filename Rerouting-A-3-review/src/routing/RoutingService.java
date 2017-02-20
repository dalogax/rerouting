package routing;
import java.util.ArrayList;
import java.util.List;

import acmeroutingco.*;
    public class RoutingService
    {
        public void route(Cargo cargo)
        {
            String origin = cargo.getOrigin();
            String destination = cargo.getDestination();

        	String route = AcmeRoutingService.getRoute(origin, destination);
        	String[] locations = route.split(",");

			cargo.setItinerary(new Itinerary());
        	for (int i = 1; i < locations.length; i++)
        	{
				cargo.getItinerary().add(
						new Leg(locations[i - 1], 
								locations[i]));
        	}
        }

        public void reroute(Cargo cargo, String reroutePoint, String newDestination)
        {
            List<Leg> legs = new ArrayList<Leg>();
            legs.addAll(cargo.getItinerary().getLegs());

            boolean pastReroutePoint = false;

            for(Leg leg:legs)
            {
                if (leg.getStart().equals(reroutePoint)) 
                		pastReroutePoint = true;
                if (pastReroutePoint) cargo.getItinerary().remove(leg);
            }
            Cargo tempCargo = new Cargo();
            tempCargo.setOrigin (reroutePoint);
            tempCargo.setDestination(newDestination);
            route(tempCargo);
            for (Leg leg :tempCargo.getItinerary().getLegs())
            {
                cargo.getItinerary().add(leg);
            }
            cargo.setDestination(newDestination);
        }
    }
