package src.core.XML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import src.core.Damage;
import src.core.Tower;

/**
 *XML Reader for reading in information about towers
 */
public class TowerXMLReader {
	//Reads in an XML Document inputFile and returns a HashMap of Towers keyed to Tower.Type
	public static HashMap<Tower.Type, Tower> readXML(String inputFile) {
		Tower.Type type;
		double cost = 0;
		double radius = 0;
		double fireRate = 0;
		double instant = 0;
		double time = 0;
		double period = 0; 
		double duration = 0; 
		double speedChange = 0;
		boolean hitsFlying = false;
		
		HashMap<Tower.Type, Tower> templateTowers = new HashMap<Tower.Type, Tower>();
		
		SAXReader reader = new SAXReader();
		Document document = null;
		
		try {
			document = reader.read(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = document.getRootElement();
		
		Iterator it = root.elementIterator("TOWER");
		while (it.hasNext()) { //looping through towers
			Element element = (Element) it.next();
			
			type = Enum.valueOf(Tower.Type.class, element.attribute("TYPE").getValue());
			//type = element.attribute("TYPE").getValue();
			cost = Double.parseDouble(element.attribute("COST").getValue());
				
			for (Iterator k = element.elementIterator(); k.hasNext();){
				Element element2 = (Element)k.next();
				if (element2.getName().equalsIgnoreCase("STATS")) {
					radius = Double.parseDouble(element2.attribute("RADIUS").getValue());
					fireRate = Double.parseDouble(element2.attribute("FIRERATE").getValue());				
				} else if (element2.getName().equalsIgnoreCase("DAMAGE")) {
					instant = Double.parseDouble(element2.attribute("INSTANT").getValue());
					time = Double.parseDouble(element2.attribute("TIME").getValue());
					period = Double.parseDouble(element2.attribute("PERIOD").getValue());
					duration = Double.parseDouble(element2.attribute("DURATION").getValue());
					speedChange = Double.parseDouble(element2.attribute("SPEEDCHANGE").getValue());
				} else if (element2.getName().equalsIgnoreCase("TARGETING")) {
					hitsFlying = Boolean.valueOf(element2.attribute("HITSFLYING").getValue());
				}
			}
			
			// Done reading in information, now place information into a template tower
			Tower tempTower = new Tower();
			Damage tempDamage = new Damage();

			tempDamage.setInstantDamage(instant);
			tempDamage.setTimeDamage(time);
			tempDamage.setEffectDuration(duration);
			tempDamage.setPeriod(period);
			tempDamage.setSpeedChange(speedChange);
			
			tempTower.setDamage(tempDamage);
			tempTower.getTargeting().setHitsFlying(hitsFlying);
			tempTower.setRadius(radius);
			tempTower.setFireRate(fireRate);
			tempTower.setPrice(cost);
			tempTower.setType(type);
			
			//add tower to list of towers
			templateTowers.put(type, tempTower);
		}
			
		return templateTowers;
	}
}
