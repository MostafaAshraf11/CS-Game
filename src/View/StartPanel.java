package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;

public class StartPanel extends JPanel implements ActionListener{
	
	private JButton romeButton;
	private JButton cairoButton;
	private JButton spartaButton;
	private String city = "";
	private JButton startButton;
	
	private JLabel lbl_name;
	private JLabel Home;
	private JTextField name;
	private HomeScreen home_screen;
	
	public StartPanel(HomeScreen home_screen){
		
		this.setSize(800,800);
		this.setLayout(null);
		this.setVisible(true);
		this.home_screen = home_screen;
		
		romeButton = new JButton("rome");
		romeButton.setBounds(350, 50, 100, 40);
		this.add(romeButton);
		
		romeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(city.equals("")){
					city= "Rome";
				}
			}
		});
		
		
		cairoButton = new JButton("cairo");
		cairoButton.setBounds(200, 50, 100, 40);
		this.add(cairoButton);
		cairoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			if(city.equals("")){
				city= "Cairo";
			}
			}
		});
		
		 spartaButton = new JButton("sparta");
		 spartaButton.setBounds(500, 50, 100, 40);
		 this.add(spartaButton);
		 spartaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			if(city.equals("")){
				city= "Sparta";
			}
			}
		});
		
		 
		 
		 lbl_name = new JLabel("");
		 lbl_name.setBounds(320,250,300,40);
		 this.add(lbl_name);
		 
		 Home = new JLabel("Empire");
		 Home.setBounds(250,320,100,40);
		 this.add(Home);
		
		 name = new JTextField ();
		 name.setBounds(300, 320, 200, 40);
		 this.add(name);
		
		 startButton = new JButton("start"); 
		 startButton.setBounds(350, 400, 100, 40);
		 this.add(startButton);
		 
		 startButton.addActionListener(this);
	
		 
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(!((name.getText().equals("")|| city.equals("")))){
			this.setVisible(false);
			try {
				Game g = new Game (name.getText(), city);
				GamePanel gameScreen = new GamePanel(name.getText(), city,g, this.home_screen);
				this.home_screen.setGame_screen(gameScreen);
				this.home_screen.add(gameScreen);
				
				gameScreen.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		else if (name.getText().equals("")&& city.equals(""))
			lbl_name.setText("please enter a name and pick a city");
		else if(!(name.getText().equals("")))
			lbl_name.setText("Please pick a city");
		else
			lbl_name.setText("Please enter a name");
			
	}
	

}
