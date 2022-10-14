package View;
import javax.swing.JButton;

import units.Army;
import engine.Player;
public class ArmyButton extends JButton {
private Army army;

	public ArmyButton(Army army){
		super();
		this.army = army;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}


}
