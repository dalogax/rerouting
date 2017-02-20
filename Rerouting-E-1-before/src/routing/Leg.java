package  routing;
/**
 * Value Object
 *
 */

public class Leg
{
	String start;
	String end;

	public Leg(String start, String end)
	{
		this.start = start;
		this.end = end;
	}
	public String end() {
		return end;
	}
	public String start() {
		return start;
	}
	public String toString(){
		return " " + start + "-" + end + " ";
	}
	
	// Auto-generated hash and equals based on value of start and end.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Leg other = (Leg) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
}
