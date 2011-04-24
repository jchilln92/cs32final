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

public class MapXMLReader {
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
