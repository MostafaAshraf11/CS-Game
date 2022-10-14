package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import units.Army;
import units.Status;
import units.Unit;
import engine.*;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;

public class GamePanel extends JPanel implements ActionListener {
private JLabel nameLabel;
private JLabel turncount;
private JLabel food;
private JButton endTurn;
private JLabel money;
private JLabel cityName;
private HomeScreen home_Screen;
private ArrayList<ArmyButton> armyButtons;
private ArrayList<CityButton> controlled_cities;
private ArrayList<CityButton> not_controlled_cities;
private JTextField display;
private Game g;
private String name;
private String city;
private JButton current_button;
private JButton prev_button;
private String currentCommand = "";
private JButton target_button;
private JButton laysiege;
private JButton autoresolve;

public GamePanel (String name, String city,Game g, HomeScreen home_screen) throws IOException {
	 super();
	 this.home_Screen = home_screen;

	 this.name = name;
	 this.city = city;
	 this.g=g;
	 controlled_cities = new ArrayList<CityButton>();
	 not_controlled_cities = new ArrayList<CityButton>();
	 armyButtons = new ArrayList<ArmyButton>();
	 //g = new Game (name, city);
	 g.getPlayer().getTreasury();
	// JTextField d = new JTextField();
	 display = new JTextField();
	 display.setBounds(200, 650, 500, 300);
	 display.setVisible(false);
	 this.add(display);
	 this.setSize(800,800);
	 this.setLayout(null);
	 nameLabel = new JLabel("player name : " + name);
	 nameLabel.setBounds(1400,5, 400, 100);
	 this.add(nameLabel);
	 ImageIcon map = new ImageIcon("Runeterra_Terrain_map.jpg");
	//Game1
	 autoresolve = new JButton("autoresolve");
	 autoresolve.setBounds(1500, 800, 200, 80);
	 autoresolve.setVisible(false);
	 autoresolve.setActionCommand("autoresolve");
	 autoresolve.addActionListener(this);
	 this.add(autoresolve);
	 
	 laysiege = new JButton("laysiege");
	 laysiege.setBounds(1500, 700, 200, 80);
	 laysiege.setVisible(false);
	 laysiege.setActionCommand("laysiege");
	 laysiege.addActionListener(this);
	 this.add(laysiege);
	 
	 target_button = new JButton("Select Target City");
	 target_button.setBounds(1500, 600, 200, 80);
	 target_button.setVisible(false);
	 target_button.setActionCommand("targetCity");
	 target_button.addActionListener(this);
	 this.add(target_button);
	 cityName = new JLabel(city);
	 JLabel map1 = new JLabel();
	 map1.setIcon(map);
	 map1.setVisible(true);
	 map1.setBounds(0, 0, 20, 20);
	 this.add(map1);
	 
	 food = new JLabel();
	 	 food.setText("Food: " +String.valueOf((int)g.getPlayer().getFood()));
	 	 food.setBounds(1400, 80, 100, 100);
	  this.add(food);
	 	 
	  money = new JLabel();
	  money.setText("Treasury: " + String.valueOf((int)g.getPlayer().getTreasury()));
	  money.setBounds(1400, 150, 220, 100);
	  this.add(money);
	 	 
	  turncount = new JLabel();
	  turncount.setText("Turncount: " + String.valueOf(g.getCurrentTurnCount()));
	  turncount.setBounds(1400,200, 100, 100);
	 	this.add(turncount);
	 	
	 	
	 	 if(g.isGameOver()){
	 		if(g.getCurrentTurnCount()> 50){
	 			JPanel x = new JPanel();
	 			this.setVisible(false);
	 			JLabel y = new JLabel("You lost");
	 			y.setBounds(200, 200, 200, 200);
	 			this.add(y);
	 			x.setVisible(true);
	 			home_screen.remove(this);
	 			home_screen.add(x);
	 		}
	 		else{
	 			JPanel x = new JPanel();
	 			this.setVisible(false);
	 			JLabel y = new JLabel("You won");
	 			y.setBounds(200, 200, 200, 200);
	 			x.add(y);
	 			x.setVisible(true);
	 			home_screen.remove(this);
	 			home_screen.add(x);
	 		}
	 	 }
	 this.refresh();
	 endTurn = new JButton("endTurn");
	 endTurn.setBounds(1300, 600, 100, 100);
	 this.add(endTurn);
	 endTurn.setVisible(true);
	 
	 endTurn.setActionCommand("endTurn");
	 endTurn.addActionListener(this); 
		
}	
//public Game getG() {
//	return g;
//}
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getActionCommand().equals("city")){
		CityButton c = (CityButton) e.getSource();
	CityPanel city_panel = new CityPanel(this.name,c.getCity(),this.city,this.home_Screen,this.g);
	this.setVisible(false);
	this.home_Screen.setCity_panel(city_panel);
	this.home_Screen.add(city_panel);
	city_panel.setVisible(true);
	}
	if(e.getActionCommand().equals("endTurn")){
		display.setText("");
		g.endTurn();
		 if(g.isGameOver()){
		 		if(g.getCurrentTurnCount()> 50){
		 			JPanel x = new JPanel();
		 			x.setBounds(150, 150, 500, 500);
		 			x.setLayout(null);
		 			this.setVisible(false);
		 			JLabel y = new JLabel("You lost");
		 			y.setBounds(200, 200, 200, 200);
		 			x.add(y);
		 			x.setVisible(true);
		 			this.home_Screen.add(x);
		 		}
		 		else{
		 			JPanel x = new JPanel();
		 			x.setBounds(150, 150, 500, 500);
		 			x.setLayout(null);
		 			this.setVisible(false);
		 			JLabel y = new JLabel("You won");
		 			y.setBounds(200, 200, 200, 200);
		 			x.add(y);
		 			x.setVisible(true);
		 		}
		 	 }
		turncount.setText("TurnCount: "+ String.valueOf(g.getCurrentTurnCount()));
		food.setText("Food: " + String.valueOf((int)g.getPlayer().getFood()) );
		money.setText("Treasury: " +  String.valueOf((int)g.getPlayer().getTreasury() ));
	}else if (e.getActionCommand().equals("idleArmy")){
		ArmyButton a = (ArmyButton) e.getSource();
		display.setText(a.getArmy().toString());
		display.setVisible(true);
		target_button.setVisible(true);
		laysiege.setVisible(true);
		autoresolve.setVisible(true);
	}else if (e.getActionCommand().equals("marchingArmy")){
		ArmyButton a = (ArmyButton) e.getSource();
		display.setText(a.getArmy().toString());
		display.setVisible(true);
		laysiege.setVisible(true);
	}else if (e.getActionCommand().equals("biesingsArmy")){
		ArmyButton a = (ArmyButton) e.getSource();
		display.setText(a.getArmy().toString());
		display.setVisible(true);
	}
	else if (e.getActionCommand().equals("EnemyCity")){
		CityButton c = (CityButton) e.getSource();
		String city_name = c.getCity().getName();
		if(prev_button!= null && prev_button instanceof ArmyButton && currentCommand.equals("targetCity")  ){
			ArmyButton b = (ArmyButton) prev_button;
			g.targetCity(b.getArmy(), city_name);
			target_button.setVisible(false);
		}
					
	}else if (e.getActionCommand().equals("autoresolve")){
		if (current_button instanceof ArmyButton){
			ArmyButton b = (ArmyButton)current_button;
			Army a = b.getArmy();
			City c = null;
			System.out.println(a.getCurrentLocation());
			for (int i = 0; i< g.getAvailableCities().size(); i++){
				if (a.getCurrentLocation().equals(g.getAvailableCities().get(i).getName())){
					c = g.getAvailableCities().get(i);
					break;
				}
			}
			try {
				System.out.println("AS:" + a);
				System.out.println("SC:" + c.getDefendingArmy());
				g.autoResolve(a, c.getDefendingArmy());
			} catch (FriendlyFireException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	else if (e.getActionCommand().equals("targetCity")){
		if (current_button instanceof ArmyButton){
			ArmyButton b = (ArmyButton)current_button;
			prev_button = b;
	}
	}else if (e.getActionCommand().equals("laysiege")){ 
		try {
		ArmyButton b = (ArmyButton)current_button;
		Army a = b.getArmy();
		City c = null;
		for (int i = 0; i< g.getAvailableCities().size(); i++){
			if (a.getCurrentLocation().equals(g.getAvailableCities().get(i).getName())){
				c = g.getAvailableCities().get(i);
				break;
			}
		}
		g.getPlayer().laySiege(a, c);
		laysiege.setVisible(false);
		} catch (TargetNotReachedException | FriendlyCityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			laysiege.setVisible(false);
		}
		
	}
	current_button = (JButton) e.getSource();
	this.currentCommand = e.getActionCommand();
	this.refresh();
	
}

public void refresh(){
	
	for (int i = 0; i < this.getComponents().length; i ++){
		 if (this.getComponent(i) instanceof ArmyButton){
			 this.remove(i);
			 i -= 1;
		 }
	 }
	for (int i = 0; i < g.getPlayer().getControlledCities().size(); i++) {
		CityButton c = new CityButton(g.getPlayer().getControlledCities().get(i));
		c.setBounds(1000, (int)(150*i), 150, 150);
		controlled_cities.add(c);
		this.add(c);
	c.setActionCommand("city");	
	c.addActionListener(this);
 }
 
 for (int i = 0; i < g.getAvailableCities().size(); i++) {
	if(!(g.getPlayer().getControlledCities().contains(g.getAvailableCities().get(i)))){
		CityButton c = new CityButton(g.getAvailableCities().get(i));
		c.setBounds(600, (int)(150*i), 120, 120);
		not_controlled_cities.add(c);
		c.setActionCommand("EnemyCity");
		c.addActionListener(this);
		this.add(c);
	}
 
	 
 }
 
 
 
 
 for (int i = 0; i < g.getPlayer().getControlledArmies().size(); i++) {
	 if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)){
	 	ArmyButton a = new ArmyButton( g.getPlayer().getControlledArmies().get(i));
	 	a.setBounds(0, 100*i, 150, 80);
	 	a.setText("IdleArmy" + (i+1));
	 	a.setActionCommand("idleArmy");
	 	a.addActionListener(this);
	 	this.add(a);
	 }
	 else if(g.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)){
		 	ArmyButton a = new ArmyButton( g.getPlayer().getControlledArmies().get(i));
		 	a.setBounds(150, 100*i, 150, 80);
		 	a.setText("MarchingArmy" +(i+1) );
		 	a.setActionCommand("marchingArmy");
		 	a.addActionListener(this);
		 	this.add(a);
		 }
	 else{
		 ArmyButton a = new ArmyButton( g.getPlayer().getControlledArmies().get(i));
		 	a.setBounds(350, 100*i, 150, 80);
		 	a.setActionCommand("biesingsArmy");
		 	a.addActionListener(this);
		 	a.setText("BiesingsArmy" +(i+1) );
		 	this.add(a);
	 }
	 this.validate();
 }
 this.repaint();
}


}
