package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import exceptions.FriendlyFireException;
import units.*;
public class BattlePanel extends JPanel implements ActionListener{
	private HomeScreen home_screen;
	private Army attacker;
	private Army defender;
	private UnitButton unit;
	private UnitButton unitdefender;
	private JLabel x;
	private JTextField display;
	private JButton attack;
	private JButton back;
	private JPanel pan;
	private String name;
	private String city;
	private Game g;
	
	public BattlePanel(String name, String city,Game g,Army attacker,Army defender,HomeScreen home_screen){
		super();
		this.setVisible(false);
		this.name = name;
		 this.city = city;
		 this.g=g;
		this.home_screen = home_screen;
		this.attacker = attacker;
		this.defender = defender;
		display = new JTextField();
		this.add(display);
		display.setVisible(false);
		pan = new JPanel();
		pan.setBounds(200, 200, 800, 800);
		pan.setVisible(false);
		
		x= new JLabel();
		x.setBounds(200, 200, 100, 100);
		pan.add(x);
		back = new JButton("back");
		back.setBounds(300, 300, 100, 100);
		back.setVisible(false);
		pan.add(back);
		back.setActionCommand("back");
		
		
		attack = new JButton("attack");
		attack.setBounds(1500, 800, 100, 100);
		attack.setVisible(false);
		this.add(attack);
		attack.setActionCommand("attack");
		attack.addActionListener(this);
		
		for(int i = 0;i<attacker.getUnits().size();i++){
			if(attacker.getUnits().size()==0){
				x = new JLabel("you lost");
				this.setVisible(false);
				home_screen.add(pan);
				pan.setVisible(true);
			}
			else{
			unit = new UnitButton(attacker.getUnits().get(i));
			if(attacker.getUnits().get(i) instanceof Archer)
				unit.setText("Archer"+ "  Level " +attacker.getUnits().get(i).getLevel() );
			else if(attacker.getUnits().get(i) instanceof Cavalry)
				unit.setText("Cavalry" + " Level " +attacker.getUnits().get(i).getLevel());
			else
				unit.setText("Infantry" + " Level " +attacker.getUnits().get(i).getLevel());
			unit.setBounds(20,100*i , 100, 100);
			this.add(unit);
			unit.setActionCommand("unit");
			unit.addActionListener(this);
			}
		}

		for(int i = 0;i<defender.getUnits().size();i++){
			if(defender.getUnits().size()==0){
				x = new JLabel("you won");
				this.setVisible(false);
				home_screen.add(pan);
				pan.setVisible(true);
			}
			unitdefender = new UnitButton(defender.getUnits().get(i));
			if(defender.getUnits().get(i) instanceof Archer)
				unitdefender.setText("Archer"+ "  Level " +defender.getUnits().get(i).getLevel() );
			else if(defender.getUnits().get(i) instanceof Cavalry)
				unitdefender.setText("Cavalry" + " Level " +defender.getUnits().get(i).getLevel());
			else
				unitdefender.setText("Infantry" + " Level " +defender.getUnits().get(i).getLevel());
			unitdefender.setBounds(1500,100*i , 100, 100);
			this.add(unitdefender);
			unitdefender.addActionListener(this);
			unitdefender.setActionCommand("unitdefender");
		}
	}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equalsIgnoreCase("unit")){
				display.setText(unit.getName()+ " currentSoldierCount: " +unit.getUnit().getCurrentSoldierCount() + "MaxSoldierCount" + unit.getUnit().getMaxSoldierCount());
				display.setVisible(true);
				 attack = new JButton("Attack");
			}
			else if(e.getActionCommand().equalsIgnoreCase("attack")){
				if(e.getActionCommand().equalsIgnoreCase("unitdefender")){
				try {
					unit.getUnit().attack(unitdefender.getUnit());
				} catch (FriendlyFireException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					unitdefender.getUnit().attack(unit.getUnit());
				} catch (FriendlyFireException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
			else if(e.getActionCommand().equalsIgnoreCase("unitdefender")){
				display.setText(unitdefender.getName()+ " currentSoldierCount: " +unitdefender.getUnit().getCurrentSoldierCount() + "MaxSoldierCount" + unitdefender.getUnit().getMaxSoldierCount());
				display.setVisible(true);
			}
			else if(e.getActionCommand().equalsIgnoreCase("back")){
				pan.setVisible(false);
				try {
					GamePanel gameScreen = new GamePanel(this.name, this.city,this.g, this.home_screen);
					pan.setVisible(false);
					home_screen.add(gameScreen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
