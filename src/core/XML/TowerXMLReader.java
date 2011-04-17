package src.core.XML;

import java.io.File;
import java.util.HashMap;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import src.core.Tower;

/**
 * XML Reader for reading in information about towers
 */
public class TowerXMLReader {	
	//Reads in an XML Document inputFile and returns a HashMap of Towers keyed to Tower.Type
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
