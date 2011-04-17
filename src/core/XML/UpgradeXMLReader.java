package src.core.XML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import src.core.Tower;

public class UpgradeXMLReader {

	public UpgradeXMLReader(){
		
	}
	
	
	public static void XMLReader(String inputFile){
		Tower.Type type;
		int level = 0;
		double cost = 0;
		double radiusChange = 0;
		double fireRateChange = 0;
		double instantChange = 0;
		double timeChange = 0;
		double periodChange = 0;
		double durationChange = 0;
		double speedEffectChange = 0;
		boolean hitsFlyingChange = false;
		
			SAXReader reader = new SAXReader();
			Document document = null;
			try {
					document = reader.read(new FileReader(inputFile));
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (DocumentException e) {
					e.printStackTrace();
			}
		
			
			Element root = document.getRootElement();
			Iterator i = root.elementIterator("TOWER");
			while (i.hasNext()){
				Element element = (Element)i.next();
				
				type = Enum.valueOf(Tower.Type.class, element.attribute("TYPE").getValue());
				level = Integer.parseInt(element.attribute("LEVEL").getValue());
				cost = Double.parseDouble(element.attribute("COST").getValue());
				
				Iterator k = element.elementIterator();
				while (k.hasNext()){
					Element element2 = (Element)k.next();
					if (element2.getName().equalsIgnoreCase("STATSCHANGE")) {
						radiusChange = Double.parseDouble(element2.attribute("RADIUS").getValue());
						fireRateChange = Double.parseDouble(element2.attribute("FIRERATE").getValue());		
					} else if (element2.getName().equalsIgnoreCase("DAMAGECHANGE")) {
	
						}
						
					}
					
					
				}
				
			}
			
	
			/*
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
			
			*/
			
			
			

		

	}
}
