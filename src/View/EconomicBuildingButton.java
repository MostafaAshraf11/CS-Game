package View;

import javax.swing.JButton;

import buildings.EconomicBuilding;

public class EconomicBuildingButton extends JButton {
	private EconomicBuilding economicBuilding;
	
	public EconomicBuildingButton(EconomicBuilding economicBuilding){
		this.economicBuilding = economicBuilding;
	}

	public EconomicBuilding getEconomicBuilding() {
		return economicBuilding;
	}

	public void setEconomicBuilding(EconomicBuilding economicBuilding) {
		this.economicBuilding = economicBuilding;
	}
	
}
