import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton play, options, quit;
	JPanel panel = new JPanel();
	JPanel xx = new JPanel();
	JFrame f = new JFrame();
	BufferedImage img, img2, btn1, btn2, btn3, btnpressed1, btnpressed2, btnpressed3;
	public MyFrame(){
		xx=this;
		try {
			  img = ImageIO.read(new File("Files/images/space.jpg"));
			  img2 = ImageIO.read(new File("Files/images/options.jpg"));
			  btn1 = ImageIO.read(new File("Files/images/buttonplay1.png"));
			  btnpressed1 = ImageIO.read(new File("Files/images/buttonplay2.png"));
			  btn2 = ImageIO.read(new File("Files/images/buttonoptions1.png"));
			  btnpressed2 = ImageIO.read(new File("Files/images/buttonoptions2.png"));
			  btn3 = ImageIO.read(new File("Files/images/buttonquit1.png"));
			  btnpressed3 = ImageIO.read(new File("Files/images/buttonquit2.png"));
				} catch (IOException e){
				  e.printStackTrace();
				  System.out.println("If you want this program to successfully work "
					  		+ "please put all the images needed for this program into /Files/images directory   ");
				  System.exit(1);
				}
		f.setVisible(true);
		f.setBounds(400,200, 1015, 840);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Osmos");
		panel.setLayout(null);
		
		xx.setLayout(new BoxLayout(xx, BoxLayout.Y_AXIS));			//button lygiavimas pagal y asi
		xx.setBorder(new EmptyBorder(new Insets(400, 0, 0, 0)));	//button centravimas (top:450)
		play=new JButton();
		play.setIcon(new ImageIcon(btn1));							//button image
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		play.setPressedIcon(new ImageIcon(btnpressed1));
		xx.add(play);
		xx.add(Box.createRigidArea(new Dimension(0, 10)));			//tarpas tarp button
		
		options=new JButton();
		options.setIcon(new ImageIcon(btn2));
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		options.setFocusPainted(false);
		options.setPressedIcon(new ImageIcon(btnpressed2));
		xx.add(options);
		
		xx.add(Box.createRigidArea(new Dimension(0, 10)));			//tarpas tarp button
		quit=new JButton();
		quit.setIcon(new ImageIcon(btn3));
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		quit.setFocusPainted(false);
		quit.setPressedIcon(new ImageIcon(btnpressed3));
		xx.add(quit);
		xx.add(Box.createRigidArea(new Dimension(0, 10)));			//tarpas tarp button
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		options.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		HandlerClass handler = new HandlerClass();
		play.addActionListener(handler);
		options.addActionListener(handler);
		quit.addActionListener(handler);
		//xx.add(this);
		
		f.add(xx);
		CameraAndAudio c = new CameraAndAudio();
		c.playMusic();
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==play) {
				f.remove(xx);
				f.add(panel);
				f.validate();
			}
			else if(event.getSource()==quit) {
				System.exit(0);
			}
			if(event.getSource()==options) {
				img=img2;
				xx.remove(options);
				xx.validate();
				xx.setBorder(new EmptyBorder(new Insets(500, 0, 0, 0)));	//button centravimas (top:500)
				repaint();
			}
		}
	}
	
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		gg.drawImage(img, -50, -150, null);
	}
	
	
	public void addComponent(Component c) {
		panel.add(c);
	}
}
