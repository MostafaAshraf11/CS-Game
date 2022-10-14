package View;

import javax.swing.JButton;

import buildings.MilitaryBuilding;

public class MilitaryBuildingButton extends JButton {
	private MilitaryBuilding militaryBuildings;
	
	public MilitaryBuildingButton(MilitaryBuilding militaryBuildings){
		this.militaryBuildings = militaryBuildings;
	}

	public MilitaryBuilding getMilitaryBuildings() {
		return militaryBuildings;
	}

	public void setMilitaryBuildings(MilitaryBuilding militaryBuildings) {
		this.militaryBuildings = militaryBuildings;
	}
}
