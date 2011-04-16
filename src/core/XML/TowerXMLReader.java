package src.core.XML;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.dom4j.*;
import org.dom4j.io.*;

import src.core.Damage;
import src.core.TargetingInfo;
import src.core.Tower;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *XML Reader for reading in information about towers
 */

public class TowerXMLReader {
	String inputFile;
	Tower.Type type;
	double cost, radius, fireRate, instant, time, period, duration, speedChange;
	boolean hitsFlying;
	
	HashMap<Tower.Type, Tower> templateTowers = new HashMap<Tower.Type, Tower>();
	
	public TowerXMLReader(String inputFile){
		this.inputFile = inputFile;
	}
	
	//Reads in an XML Document inputFile and returns a HashMap of Towers keyed to Tower.Type
	public HashMap<Tower.Type, Tower> readXML() throws FileNotFoundException, DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new FileReader(inputFile));
		
		Element root = document.getRootElement();
		
		for (Iterator i = root.elementIterator("TOWER"); i.hasNext();){ //looping through towers
			Element element = (Element)i.next();
			
			type = Enum.valueOf(Tower.Type.class, element.attribute("TYPE").getValue());
			//type = element.attribute("TYPE").getValue();
			cost = Double.parseDouble(element.attribute("COST").getValue());
				
				for (Iterator k = element.elementIterator(); k.hasNext();){
					Element element2 = (Element)k.next();
					if (element2.getName().equalsIgnoreCase("STATS")){
						radius = Double.parseDouble(element2.attribute("RADIUS").getValue());
						fireRate = Double.parseDouble(element2.attribute("FIRERATE").getValue());				
					}
					else if (element2.getName().equalsIgnoreCase("DAMAGE")){
						instant = Double.parseDouble(element2.attribute("INSTANT").getValue());
						time = Double.parseDouble(element2.attribute("TIME").getValue());
						period = Double.parseDouble(element2.attribute("PERIOD").getValue());
						duration = Double.parseDouble(element2.attribute("DURATION").getValue());
						speedChange = Double.parseDouble(element2.attribute("SPEEDCHANGE").getValue());
					}
					else if (element2.getName().equalsIgnoreCase("TARGETING")){
						hitsFlying = Boolean.valueOf(element2.attribute("HITSFLYING").getValue());
					}
				}
			
			//Done reading in information, now place information into a template tower
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
				
			System.out.println("TOWER");
			System.out.println(type + " " + cost);
			System.out.println(radius + " " + fireRate);
			System.out.println(instant + " " + time + " " + period + " " + duration + " " + speedChange);
			System.out.println(hitsFlying);
		}
			
		return templateTowers;
	}
			
			
		
	
	public static void main(String[] args) throws FileNotFoundException, DocumentException{
		TowerXMLReader reader = new TowerXMLReader("/home/al63/exampleTower.xml");
		HashMap<Tower.Type, Tower> test = reader.readXML();
		
		for (Tower t: test.values()){
			System.out.println(t.getType());
		}
		
	}
		
		
	

}
