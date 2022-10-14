package View;

import javax.swing.JButton;
import engine.City;
import engine.Player;
public class CityButton extends JButton {
	private City city;
	
	public CityButton(City city){
		super();
		this.city = city;
		this.setText(city.getName());
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
