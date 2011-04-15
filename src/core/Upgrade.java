package src.core;


/**
 * 
 * The upgrade class holds all of the changes a tower upgrade can apply onto a tower. 
 */
public class Upgrade implements IPurchasable {
	
	private double instantDamageChange;
	private double radiusChange;
	private double investmentChange;
	private double price;
	//need to add fields for damage over time, changes to speed changes, etc
	
	public Upgrade(){
		//Change later to use an XML reader to get these values
		instantDamageChange = 0;
		radiusChange = 0;
		investmentChange = 0;
		this.setPrice(0);
	}
	
	public double getInstantDamageChange(){
		return instantDamageChange;
	}
	
	public double getRadiusChange(){
		return radiusChange;
	}
	
	public double getInvestmentChange(){
		return investmentChange;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double p) {
		price = p;
	}
	
	

}
