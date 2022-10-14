package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import exceptions.*;

public class HomeScreen extends JFrame{
	
	private StartPanel start_panel;
	private GamePanel game_screen;
	private CityPanel city_panel;
	//private BattlePanel battle_panel;
	
	
	public StartPanel getStart_panel() {
		return start_panel;
	}


	public void setStart_panel(StartPanel start_panel) {
		this.start_panel = start_panel;
	}


	public GamePanel getGame_screen() {
		return game_screen;
	}


	public void setGame_screen(GamePanel game_screen) {
		this.game_screen = game_screen;
	}


	public static  ImageIcon resize(ImageIcon i , int newWidth, int newHeight) {
		
		Image img = i.getImage();
		Image resized = img.getScaledInstance( newWidth, newHeight,java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon (resized);
		
	}
	
		
	public HomeScreen() {	
		super();
		start_panel = new StartPanel(this);
		this.add(start_panel);
		
		//this.city_panel = new CityPanel(this);
		//this.add(city_panel);
		
		//this.battle_panel = new BattlePanel(this);
		//this.add(battle_panel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	
public CityPanel getCity_panel() {
		return city_panel;
	}


	public void setCity_panel(CityPanel city_panel) {
		this.city_panel = city_panel;
	}


	public static void main(String[] args){
		HomeScreen home_screen = new HomeScreen();
		
	}

}
