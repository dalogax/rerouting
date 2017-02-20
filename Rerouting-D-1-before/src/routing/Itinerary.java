package routing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
    public class Itinerary
    {
        private final List<Leg> legs;

    	public Itinerary(List<Leg> legs) {
    		this.legs=new ArrayList<Leg>();
    		this.legs.addAll(legs);
    	}

		public Itinerary(Leg... legs) {
    		this(Arrays.asList(legs));
		}

        public List<Leg> legs()
        {
            return Collections.unmodifiableList(legs); 
        }

		public Itinerary truncatedAt(String cutPoint) {
			return new Itinerary(legs.subList(0, indexOf(cutPoint)));
		}
		
		private int indexOf(String reroutePoint) {
			for (Leg leg : legs) {
				if (leg.start().equals(reroutePoint)) 
					return legs.indexOf(leg); 
				if (leg.end().equals(reroutePoint)) 
					return legs.indexOf(leg)+1; 
			}
			return -1;
		}

		public Itinerary extendedOn(List<Leg> extentionLegs) {
			List<Leg> extendedLegs = new ArrayList<Leg>();
			extendedLegs.addAll(legs);
			extendedLegs.addAll(extentionLegs);
			return new Itinerary(extendedLegs);
		}

		public boolean isConnected() {
			if (legs.size() < 2) return true;
			for (int i = 1; i < legs.size(); i++) {
				Leg left = legs.get(i-1);
				Leg right = legs.get(i);
				if (!left.end().equals(right.start())) return false;
			}
			return true;
		}
		
		public boolean goesThrough(String location) {
			return indexOf(location)!=-1;
		}
		
		public Leg lastLeg() {
			return legs.get(legs.size()-1);
		}
		
		public String start() {
			return legs.get(0).start();
		}

		public String end() {
			return lastLeg().end();
		}
		
		public String toString() {
			String result = "";
			for (Leg leg : legs) {
				result = result + leg.toString();
			}
			return result;
		}
		
    	//Auto-generated hash and equals based on value of legs
    	@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((legs == null) ? 0 : legs.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Itinerary other = (Itinerary) obj;
			if (legs == null) {
				if (other.legs != null)
					return false;
			} else if (!legs.equals(other.legs))
				return false;
			return true;
		}

    }
