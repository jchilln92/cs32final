package src.core.xml;

import java.io.File;
import java.util.HashMap;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import src.core.Tower;

/**
 * XML Reader for reading in information about towers and their upgrades.
 * The format for the XML is defined by annotations in the relevant classes.
 */
public class TowerXMLReader {	
	/**
	 * Generates the basic information for the towers available in the game
	 * @param inputFile The Filepath for the XML file to be read
	 * @return A database for the tower information
	 */
	public static HashMap<Tower.Type, Tower> readXML(String inputFile) {
		Serializer serializer = new Persister();
		File input = new File(inputFile);
		
		Towers t = null;
		try {
			t = serializer.read(Towers.class, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		HashMap<Tower.Type, Tower> towerTemplates = new HashMap<Tower.Type, Tower>();
		for (Tower tower : t.getTowers()) {
			towerTemplates.put(tower.getType(), tower);
		}
		
		return towerTemplates;
	}
}
