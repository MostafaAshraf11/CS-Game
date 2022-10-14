package View;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import units.*;
import buildings.*;
import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.*;
import exceptions.NotEnoughGoldException;

public class CityPanel extends JPanel implements ActionListener {
	private JLabel nameLabel;
	private JButton t;
	private JButton z;
	private JComboBox<String> x;
	private JLabel turncount;
	private JLabel food;
	private JLabel money;
	private HomeScreen home_screen;
	//private JButton building;
	private JButton Farm1;
	private JButton build;
	private ArrayList<EconomicBuildingButton> economicBuilding;
	private ArrayList <MilitaryBuildingButton> militaryBuilding;
	private JButton back;
	private String city;
	private String name;
	private Game g;
	private JButton recruit;
	private JButton upgrade;
	private JTextField display;
	//private ArrayList<Unit> unitsofDefendingArmy;
	private ArrayList<UnitButton> units;
	private ArrayList<ArmyButton> armyButtons;
	City c = null;
	final int test=0;
	private JButton endTurn;
	private JButton current_button;
	private JButton prev_button;
	private JButton intiateArmy;
	private City city_object;
	private JButton relocateArmy;
	private String currentCommand = "";
	
	public CityPanel(String name,City city_object ,String city,HomeScreen home_screen,Game g){
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setLayout(null);
		this.city_object = city_object;
		this.home_screen = home_screen;
		economicBuilding = new ArrayList <EconomicBuildingButton>();
		militaryBuilding = new ArrayList <MilitaryBuildingButton>();
		armyButtons = new ArrayList<ArmyButton>();
		units = new ArrayList<UnitButton>();
		this.name= name;
		this.city = city;
		this.g= g;
		this.setVisible(false);
		//unitsofDefendingArmy = new ArrayList<Unit>();
	
		display = new JTextField();
		 display.setBounds(100, 800, 500, 200);
		 this.add(display);
		 display.setVisible(false);
		 

		nameLabel = new JLabel("player name : " + name);
		 nameLabel.setBounds(1300,5, 400, 100);
		 this.add(nameLabel);
		 food = new JLabel();
		 food.setText("Food: " +String.valueOf(g.getPlayer().getFood()));
	 	 food.setBounds(1300, 80, 100, 100);
	  this.add(food);
	  
	  money = new JLabel();
	  money.setText("Treasury: " + String.valueOf(g.getPlayer().getTreasury()));
	  money.setBounds(1300, 150, 100, 100);
	 this.add(money);
		 
	  turncount = new JLabel();
	  turncount.setText("Turncount: " + String.valueOf(g.getCurrentTurnCount()));
	  turncount.setBounds(1300,200, 100, 100);
	 	this.add(turncount);
	 	
		build = new JButton("Build");
		build.setBounds(1100, 500, 100, 70);
		this.add(build);
		build.addActionListener(this);
		build.setActionCommand("build");
			
		upgrade = new JButton("upgrade");
		upgrade.setBounds(1100, 300, 100, 70);
		this.add(upgrade);
		upgrade.setActionCommand("upgradeBuilding");
		upgrade.addActionListener(this);


		recruit= new JButton("recruit");
		recruit.setBounds(1100, 100,100, 70);
		this.add(recruit);
		recruit.setActionCommand("recruit");
		recruit.addActionListener(this);

		
		back = new JButton("Back");
		back.setBounds(1300, 700, 100, 70);
		this.add(back);
		back.setActionCommand("back");
		back.addActionListener(this);
		this.validate();
		String[] choices = {"","Farm", "ArcheryRange","Stable","Market","Barracks"};
		x = new JComboBox<String>(choices);
	    x.setBounds(100, 600, 120, 80);
	    x.setVisible(false);
	    this.add(x);
	    z = new JButton("cancel");
	    z.setBounds(250, 600, 100, 50);
	    z.setVisible(false);
	    this.add(z);
	    z.setActionCommand("cancel");
	    z.addActionListener(this);
	    z.setVisible(false);
	     t = new JButton("ok");
	    t.setBounds(350, 600, 100, 50);
	    t.setActionCommand("ok");
	    t.addActionListener(this);
	    t.setVisible(false);
	    this.add(t);
		
		 endTurn = new JButton("endTurn");
		 endTurn.setBounds(1300, 600, 100, 70);
		 this.add(endTurn);
		 endTurn.setVisible(true);
		 
		 endTurn.setActionCommand("endTurn");
		 endTurn.addActionListener(this); 
		 

			intiateArmy = new JButton("IntiateArmy");
			intiateArmy.setBounds(1100,700 , 100, 70);
			intiateArmy.setVisible(false);
			this.add(intiateArmy);
			intiateArmy.addActionListener(this);
			intiateArmy.setActionCommand("intiateArmy");
			
			
			relocateArmy = new JButton("relocateArmy");
			relocateArmy.setBounds(1100, 800, 100, 70);
			relocateArmy.setVisible(false);
			this.add(relocateArmy);
			relocateArmy.addActionListener(this);
			relocateArmy.setActionCommand("relocateArmy");
			
			
		 this.refresh();
		 
//						u.addActionListener(new ActionListener() {
//							
//							@Override
//							public void actionPerformed(ActionEvent arg0) {
//								String x = "";
//								if(u.getUnit() instanceof Archer ){
//									 x = "Archer";
//								}
//								else if (u.getUnit() instanceof Cavalry ){
//									x = "Cavalry";
//								}
//								else
//									x = "Infantry";
//									
//								String s2 = ( x + "\n");
//					
//								String s1 = ("Level:  " +u.getUnit().getLevel() + " currentSoldierCount:  "+ u.getUnit().getCurrentSoldierCount() + "  MaxSoldierCount: " + u.getUnit().getMaxSoldierCount());
//								s2 = s2 +s1;
//								display.setText(s2);
//								display.setVisible(true);
//								
//							}
//						});
		 this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("back")){
		try{
		GamePanel gameScreen = new GamePanel(this.name, this.city,this.g, this.home_screen);
		this.setVisible(false);
		//this.home_screen.(gameScreen);
		this.home_screen.add(gameScreen);
		gameScreen.setVisible(true);
		}
		 catch (IOException a) {
				// TODO Auto-generated catch block
				a.printStackTrace();
		 	}
		}
		
		else if(e.getActionCommand().equalsIgnoreCase("build")){
			String[] choices = {"","Farm", "ArcheryRange","Stable","Market","Barracks"};
			 	
			    x.setVisible(true);
			    z.setVisible(true);
			    t.setVisible(true);
			    this.validate();
		}
		else if(e.getActionCommand().equalsIgnoreCase("cancel")){
				x.setVisible(false);
				z.setVisible(false);
				t.setVisible(false);
			    	this.validate();
			    }else if(e.getActionCommand().equals("endTurn")){
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
		   		 			this.home_screen.add(x);
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
		   	    food.setText("Food: " +String.valueOf((int)g.getPlayer().getFood()));
		   		money.setText("Treasury: " +  String.valueOf((int)g.getPlayer().getTreasury() ));
		   	}
			    else if((e.getActionCommand().equalsIgnoreCase("ok"))){
			    	if(x.getSelectedItem().toString().equals("")){
			    		
			    	}
			    	else{
			    	if(x.getSelectedItem().toString().equals("Farm")){
			    		try {
							g.getPlayer().build("Farm", city);
						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	}
			    	else if (x.getSelectedItem().toString().equals("Stable")){
			    		try {
							g.getPlayer().build("Stable", city);
						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
			    	}
			    	else if (x.getSelectedItem().toString().equals("Barracks")){
			    		try {
							g.getPlayer().build("Barracks", city);
						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
			    	}
			    	else if (x.getSelectedItem().toString().equals("ArcheryRange")){
			    		try {
							g.getPlayer().build("ArcheryRange", city);
						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
			    		
			    	}
			    	else if (x.getSelectedItem().toString().equals("Market")){
			    		try {
							g.getPlayer().build("Market", city);
						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
			    	}
			    	 money.setText("Treasury: " + String.valueOf(g.getPlayer().getTreasury()));
			    	 x.setVisible(false);
					z.setVisible(false);
					t.setVisible(false);
			    	this.validate();
			    }
		}else if (e.getActionCommand().equals("economicBuilding")){
			EconomicBuildingButton b = (EconomicBuildingButton)e.getSource();
			System.out.println(b.getEconomicBuilding());
			String x = "";
			if(b.getEconomicBuilding() instanceof Farm ){
				 x = "Farm";
			}
			else if (b.getEconomicBuilding() instanceof Market ){
				x = "Market";
			}
			String s2 = x + "\n";

			String s1 = ("Building level: " +b.getEconomicBuilding().getLevel()+ "  upgrade cost : " + b.getEconomicBuilding().getUpgradeCost());
			s2 = s2 +s1;
			display.setText(s2);
			display.setVisible(true);
			
		}else if (e.getActionCommand().equals("MilitaryBuilding")){
			MilitaryBuildingButton m = (MilitaryBuildingButton)e.getSource();
			String x = "";
			if(m.getMilitaryBuildings() instanceof ArcheryRange ){
				 x = "ArcheryRange";
			}
			else if (m.getMilitaryBuildings() instanceof Stable ){
				x = "Stable";
			}
			else
				x = "Barracks";
				
			String s2 = x + "\n";

			String s1 = ("Building level: " +m.getMilitaryBuildings().getLevel()+ "upgrade cost: "+ m.getMilitaryBuildings().getUpgradeCost() + " Recruitment cost: " + m.getMilitaryBuildings().getRecruitmentCost());
			s2 = s2 +s1;
			display.setText(s2);
			display.setVisible(true);
		}
		else if (e.getActionCommand().equals("upgradeBuilding")){
			if (current_button instanceof EconomicBuildingButton){
				EconomicBuildingButton b = (EconomicBuildingButton)current_button;
				try {
					b.getEconomicBuilding().upgrade();
	//				this.refresh();
				} catch (BuildingInCoolDownException | MaxLevelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (e.getActionCommand().equals("recruit")){
			if (current_button instanceof MilitaryBuildingButton){
				MilitaryBuildingButton b = (MilitaryBuildingButton)current_button;
				if (current_button instanceof MilitaryBuildingButton){
						if (b.getMilitaryBuildings() instanceof ArcheryRange){
							try {
								g.getPlayer().recruitUnit("archer", this.city);
							} catch (BuildingInCoolDownException
									| MaxRecruitedException
									| NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}else if (b.getMilitaryBuildings() instanceof Stable){
							try {
								g.getPlayer().recruitUnit("cavalry", this.city);
							} catch (BuildingInCoolDownException
									| MaxRecruitedException
									| NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
						else if (b.getMilitaryBuildings() instanceof Barracks){
							try {
								g.getPlayer().recruitUnit("infantry", this.city);
							} catch (BuildingInCoolDownException
									| MaxRecruitedException
									| NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					
				}
			}
		}else if (e.getActionCommand().equals("Unit")){
			UnitButton u = (UnitButton)e.getSource();
			String x = "";
			if(u.getUnit() instanceof Archer ){
				 x = "Archer";
			}
			else if (u.getUnit() instanceof Cavalry ){
				x = "Cavalry";
			}
			else
				x = "Infantry";
				
			String s2 = ( x + "\n");

			String s1 = ("Level:  " +u.getUnit().getLevel() + " currentSoldierCount:  "+ u.getUnit().getCurrentSoldierCount() + "  MaxSoldierCount: " + u.getUnit().getMaxSoldierCount());
			s2 = s2 +s1;
			display.setText(s2);
			display.setVisible(true);
			intiateArmy.setVisible(true);
			relocateArmy.setVisible(true);
		}
		else if (e.getActionCommand().equals("intiateArmy")){
			if (current_button instanceof UnitButton){
				UnitButton b = (UnitButton)current_button;
				g.getPlayer().initiateArmy(city_object, b.getUnit());
		}
		
		}
		else if (e.getActionCommand().equals("AttackingArmy")){
			ArmyButton a = (ArmyButton)e.getSource();
			Army army = a.getArmy();
			if(prev_button!= null && prev_button instanceof UnitButton && currentCommand.equals("relocateArmy")  ){
				UnitButton b = (UnitButton) prev_button;
				try {
					army.relocateUnit(b.getUnit());
				} catch (MaxCapacityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				prev_button = null;
			}
			
			display.setText(army.toString());
			display.setVisible(true);
			intiateArmy.setVisible(true);
		}
		
		else if (e.getActionCommand().equals("relocateArmy")){
			if (current_button instanceof UnitButton){
				UnitButton b = (UnitButton)current_button;
				prev_button = b;
		}
		
		}
		
		
		this.validate();
		current_button = (JButton) e.getSource();
		this.currentCommand = e.getActionCommand();
		this.refresh();
			
			
		}

	public void refresh(){
		
		for (int i = 0; i < g.getPlayer().getControlledCities().size(); i++) {
			if(g.getPlayer().getControlledCities().get(i).getName().equals(city)){
			 for(int j = 0;j<g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().size();j++){
				 EconomicBuildingButton e = new EconomicBuildingButton(g.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j));
				 e.setBounds((100), (100*j), 180, 70);
				 e.setText("Economical Building");
				 economicBuilding.add(e);
				 e.setVisible(true);
				 this.add(e);
				 this.validate();
				 e.setActionCommand("economicBuilding");
				 e.addActionListener(this);

				 }
				}

			 }
		 for (int i = 0; i < g.getPlayer().getControlledCities().size(); i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals(city)){
				 for(int j = 0;j<g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size();j++){
					 MilitaryBuildingButton m = new MilitaryBuildingButton(g.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j));
					 m.setBounds((300), (100*j), 180, 70);
					 m.setText("Military Building");
					 militaryBuilding.add(m);
					 m.setVisible(true);
					 this.add(m);
					 m.setActionCommand("MilitaryBuilding");
					 m.addActionListener(this);

					 }
					}
				 }
		 
		 for (int i = 0; i < this.getComponents().length; i ++){
			 if (this.getComponent(i) instanceof UnitButton){
				 this.remove(i);
				 i -= 1;
			 }
		 }
		 
		 for (int i = 0; i < g.getPlayer().getControlledCities().size(); i++) {
				if(g.getPlayer().getControlledCities().get(i).getName().equals(city)){
					System.out.println(g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits());
					for(int j =0;j<g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().size();j++){
						Unit unit =  g.getPlayer().getControlledCities().get(i).getDefendingArmy().getUnits().get(j);
						UnitButton u = new UnitButton(unit);
						u.setText("Defending Army Unit");
						units.add(u);
						u.setBounds(500,(100*j), 180, 70);
						u.setActionCommand("Unit");
						u.addActionListener(this);
						u.setVisible(true);
						this.add(u);
					}
				}
		 }
		 for (int i = 0; i < g.getPlayer().getControlledArmies().size(); i++) {
				Army a = g.getPlayer().getControlledArmies().get(i);
				
				if(a.getCurrentLocation().equals(this.city)){
				ArmyButton b = new ArmyButton(a);	
				armyButtons.add(b);
				b.setBounds(700,(100*i), 180, 70);
				b.setText("AttackingArmy" + (i+1) );
				b.setActionCommand("AttackingArmy");
				b.addActionListener(this);
				b.setVisible(true);
				this.add(b);
				}
		}
		
		 
		 this.repaint();
		 
		
	}
	

		}
