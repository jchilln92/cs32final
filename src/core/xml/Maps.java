package src.core.xml;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import src.core.Map;

/**
 * A helper class that allows reading a list of maps in from an XML file.
 * @see Towers
 * @see MapXMLReader
 */
@Root
public class Maps {
	@ElementList(inline=true)
	private List<Map> maps;
		
	public List<Map> getMaps() {
		return maps;
	}
}
