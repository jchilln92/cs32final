package src.core.xml;

import java.io.File;
import java.util.HashMap;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import src.core.Creep;

/**
 * XML Reader for reading in information about creep and their upgrades.
 * The format for the XML is defined by annotations in the relevant classes.
 */
public class CreepXMLReader {
	
	/**
	 * Generates the basic information/templates for creeps to be used in the game.
	 * @param inputFile The Filepath for the XML file to be read
	 * @return A database for the creep information
	 */
	public static HashMap<Creep.Type, Creep> readXML(String inputFile) {
		Serializer serializer = new Persister();
		File input = new File(inputFile);
		
		Creeps c = null;
		try {
			c = serializer.read(Creeps.class, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		HashMap<Creep.Type, Creep> creepTemplates = new HashMap<Creep.Type, Creep>();
		for (Creep creep : c.getCreeps()) {
			creepTemplates.put(creep.getType(), creep);
		}
		
		return creepTemplates;
	}
}
