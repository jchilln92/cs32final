package src.core.XML;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import src.core.Tower;

@Root
/**
 * This class is a small helper that allows us to read in a list of towers from XML.
 */
public class Towers {
	@ElementList(inline=true)
	private List<Tower> towers;
	
	public List<Tower> getTowers() {
		return towers;
	}
}
