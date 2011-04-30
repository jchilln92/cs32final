package src.core.xml;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import src.core.Creep;

/**
 * Helper class to allow reading a list of creeps
 * @see Towers
 * @see Maps
 */
@Root
public class Creeps {
	@ElementList(inline=true)
	private List<Creep> creeps;
	
	public List<Creep> getCreeps() {
		return creeps;
	}
}
