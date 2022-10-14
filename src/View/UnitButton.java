package View;
import javax.swing.JButton;

import units.*;
import engine.*;
public class UnitButton extends JButton {
	private Unit unit;

		public UnitButton(Unit unit){
			super();
			this.unit = unit;
		}

		public Unit getUnit() {
			return unit;
		}

		public void setUnit(Unit unit) {
			this.unit = unit;
		}

		
}
