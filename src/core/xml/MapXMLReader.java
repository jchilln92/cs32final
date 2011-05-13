package src.core.xml;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import src.core.Map;

/**
 * XML Reader for reading in information about maps.
 * The format for the XML is defined by annotations in the relevant classes.
 */
public class MapXMLReader {
	/**
	 * Generates the basic information for the maps to be used in the game
	 * @param inputFile The Filepath for the XML file to be read
	 * @return A list of the available maps
	 */
	public static ArrayList<Map> readXML(String inputFile) {
		Registry registry = new Registry();
		Strategy strategy = new RegistryStrategy(registry);
		Serializer serializer = new Persister(strategy);
		File input = new File(inputFile);
		
		Maps m = null;
		try {
			registry.bind(Point2D.Double.class, PointConverter.class);
			m = serializer.read(Maps.class, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return new ArrayList<Map>(m.getMaps());
	}
}
