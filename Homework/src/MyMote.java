import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class MyMote extends Mote{
	private static final long serialVersionUID = 1L;
	List<OtherMote> list;
	 ListIterator<OtherMote> ltr;
	 private boolean n1, n2, marked, flag, flag2, flag3, flag4;
	 private double num3, num4, rad1, rad2, flagSize, flagSize2, flagSize3, myMax;
	 private int index, q;
	 volatile boolean running=true;
	 CameraAndAudio camera = new CameraAndAudio(1d);
	 Image img5, img6;
	 BufferedImage btn1, btnpressed1, btn2, btnpressed2;
	 JButton restart, quit;
	 JPanel xx;
	 
	 public MyMote(){
		 HandlerClass handler = new HandlerClass();
		 this.addMouseListener(handler);
		 this.addMouseWheelListener(handler);
		 init();
		 list = new ArrayList<OtherMote>();
		 mythread = new Thread(this);
		 mythread.start();
		 CreateRandomMotes();
		 UploadPhotos();
	 }
	 
	 public void init() {
		 speed=0.05;
		 x=470;
		 y=330;
		 size=40;
		 flag=true;
		 flag2=true;
		 flag3=true;
		 flag4=false;
		 marked = true;
	 }
	 
	 //kuriu othermotes rankiniu budu
	 public void CreateRandomMotes(){
			list.add(new OtherMote(200, 300, 45));
			list.add(new OtherMote(450, 245, 35));
			list.add(new OtherMote(700, 524, 15));
			list.add(new OtherMote(452, 500, 45));
			list.add(new OtherMote(115, 678, 50));
			list.add(new OtherMote(700, 100, 70));
			list.add(new OtherMote(800, 550, 45));
			list.add(new OtherMote(200, 100, 40));
			list.add(new OtherMote(200, 500, 15));
			list.add(new OtherMote(400, 650, 45));
			list.add(new OtherMote(850, 300, 50));
			list.add(new OtherMote(-200, 0, 100));
			list.add(new OtherMote(-150, 300, 35));
			list.add(new OtherMote(-180, 560, 50));
			list.add(new OtherMote(-400, 700, 20));
			list.add(new OtherMote(-300, 950, 52));
			list.add(new OtherMote(-200, 800, 30));
			list.add(new OtherMote(200, 900, 90));
			list.add(new OtherMote(550, 915, 20));
			list.add(new OtherMote(800, 1000, 75));
			list.add(new OtherMote(815, 770, 18));
			list.add(new OtherMote(1000, 800, 30));
			list.add(new OtherMote(1200, 1100, 25));
			list.add(new OtherMote(1150, 950, 50));
			list.add(new OtherMote(1215, 700, 20));
			list.add(new OtherMote(1300, 500, 80));
			list.add(new OtherMote(1100, 200, 40));
			list.add(new OtherMote(1150, -100, 58));
			list.add(new OtherMote(900, -150, 30));
			list.add(new OtherMote(1170, -300, 45));
			list.add(new OtherMote(500, -350, 120));
			list.add(new OtherMote(400, -130, 34));
			list.add(new OtherMote(0, -330, 150));
			list.add(new OtherMote(-300, -370, 45));
			list.add(new OtherMote(-350, -100, 60));
			list.add(new OtherMote(600, 350, 50));
			list.add(new OtherMote(500, 100, 35));
		}
	 
	 public void UploadPhotos(){
		 try {
			  img = ImageIO.read(new File("Files/images/stars2.jpg"));
			  img2 = ImageIO.read(new File("Files/images/nMyMote.png"));
			  img3 = ImageIO.read(new File("Files/images/MyMoteChange.png"));
			  img4 = ImageIO.read(new File("Files/images/frame.png"));
			  img5 = ImageIO.read(new File("Files/images/Over.png"));
			  img6 = ImageIO.read(new File("Files/images/youwon.png"));
			  btn1 = ImageIO.read(new File("Files/images/restart.png"));
			  btnpressed1 = ImageIO.read(new File("Files/images/restart2.png"));
			  btn2 = ImageIO.read(new File("Files/images/buttonquit1.png"));
			  btnpressed2 = ImageIO.read(new File("Files/images/buttonquit2.png"));
				} catch (IOException e){
				  e.printStackTrace();
				  System.out.println("If you want this program to successfully work "
					  		+ "please put all the images needed for this program into /Files/images directory   ");
				  System.exit(1);
				}
		 }
	 
	 public void action(MouseEvent event) {
		 if(SwingUtilities.isLeftMouseButton(event)) {
			changeStatus(true);
			//mycoordinates funkcijoje esantis button ifas pradeda veikti tik nuo sios akimirkos (flag4=true), kitu atveju nullptrexception
			flag4=true;
			//peles paspaudimas + koordinaciu sutvarkymas (del translate issibalansavo)
			prevX=x+(event.getX()-this.getWidth()/2);
			prevY=y+(event.getY()-this.getHeight()/2);
			System.out.println("x= "+event.getX()+" y= "+event.getY());
			distanceX=prevX-x;
			distanceY=prevY-y;
			//reduce MyMote size
			reduceSize();
			//polar coords
			polarCoords(distanceX, distanceY);
			list.add(new OtherMote(x, y, size, this.getWidth(), this.getHeight()));
			index=list.size()-1;
			list.get(index).polarCoords(distanceX, distanceY);
			list.get(index).init(x, y, size);
		 }
	 }
	 
	//mouse eventai
	private class HandlerClass implements MouseListener, MouseWheelListener, ActionListener{
		public void mouseClicked(MouseEvent event) {
			//pergales arba pralaimejimo atveju nebegaliu spausti knopkes
			if(size>15 && size<=myMax+20){
				action(event);
			}
			repaint();
		}
		public void mousePressed(MouseEvent event) {
			//getStatus().setText("you pressed");
		}
		public void mouseReleased(MouseEvent event) {
			//getStatus().setText("you released");
		}
		public void mouseEntered(MouseEvent event) {
			//getStatus().setText("you entered the area");
		}
		public void mouseExited(MouseEvent event) {
			//getStatus().setText("the mouse left the window");
		}
		public void mouseWheelMoved(MouseWheelEvent e)
	    {
			//zoomas
			camera.ZoomInAndOut(e);
            repaint();
	    }
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==quit) {
				System.exit(0);
			}else if(event.getSource()==restart) {
				//restartinu zaidima
				toRemove();
				tanX=0;
				tanY=0;
				list.clear();
				init();
				CreateRandomMotes();
			}
		}
	}
	
	public void toRemove() {
		this.remove(xx);
	}
	
	public void polarCoords(double distanceX, double distanceY) {
		angle = Math.atan2(distanceY, distanceX) * 180 / Math.PI;
		tanX += -Math.cos(angle * Math.PI/180) * 2;
		tanY += -Math.sin(angle * Math.PI/180) * 2;
	}
	
	public void init(double x, double y, double size) {	
		x=x+size/2;
		y=y+size/2;
	}
	
	public double getXX() {
		return x;
	}
	
	public double getYY() {
		return y;
	}
	
	public void reduceSize() {
		if(size>5)
			size=size-0.7;
	}
	
	public void increaseSize() {
		size=size+0.2;
	}
	
	public void reduceSize(double size) {
		if(size>5)
			this.size=this.size-0.4;
	}
	
	// susikuriu MyMote+OtherMote circle
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double zoom = camera.getZoom();
		
		Graphics2D g2d = (Graphics2D) g.create();
		camera.calculate(getWidth(), getHeight());
        
        AffineTransform at = new AffineTransform();
        at.translate(camera.getAnchorX(), camera.getAnchorY());
        at.scale(zoom, zoom);

        g2d.setTransform(at);
        g2d.drawImage(img, -150, -150, null);
        

		g2d.translate((int)(this.getWidth()/2-x), (int)(this.getHeight()/2-y));			//judina mapa
		
		for(OtherMote mote: list)
			mote.paintComponent(g, g2d);
		if(flag==true)
			g2d.drawImage(img2, (int)x, (int)y, (int)size, (int)size, this);
		else
			g2d.drawImage(img3, (int)x, (int)y, (int)size, (int)size, this);
			
		g2d.drawImage(img4, -1244, -1240, 3456, 3256, null);
		//jeigu pralaimejau
		for(OtherMote mote: list)
			if(myMax<mote.getMoteSize())
				myMax=mote.getMoteSize();
		if(size<=15){
			g.drawImage(img5, 0, 20, null);
			//po pralaimejimo mote nebesvies
			for(OtherMote mote: list) {
				mote.sendFlagToOtherMoteX(true);
				mote.sendFlagToOtherMoteY(true);
			}
		 }else if(size>myMax+20){	//max = didziausias othermote is visu
			 //jeigu laimejau
			 g.drawImage(img6, 0, 20, null);
				//po pralaimejimo mote nebesvies
			for(OtherMote mote: list) {
				mote.sendFlagToOtherMoteX(true);
				mote.sendFlagToOtherMoteY(true);
			}
			//nuimu mymote svietima
			flag=true;
		 
		 }else{
			deleteMote();
			checkOtherMote();
			checkFlag();
		 }
		g2d.dispose();
	}
	
	
	public boolean moteCoordArea(double kX, double kY, double radius, int q) {
		num1=x+size/2;
		num2=y+size/2;
		distance=Math.sqrt((kX-num1)*(kX-num1)+(kY-num2)*(kY-num2));
		if (distance > (radius + size/2)) 
		{
		    // No overlap
		    
		    return false;
		}
		else if ((distance <= Math.abs(radius - size/2))) 
		{
		    // Inside
		    return true;
		}
		else if ((distance <= radius + size/2))
		{
		   // Overlap
		   return true;
		}
		else
			return false;
	}
	
	public boolean moteCoordArea(int q, int i) {
		num1=list.get(q).getXX()+list.get(q).getMoteSize()/2;
		num2=list.get(q).getYY()+list.get(q).getMoteSize()/2;
		num3=list.get(i).getXX()+list.get(i).getMoteSize()/2;
		num4=list.get(i).getYY()+list.get(i).getMoteSize()/2;
		rad1=list.get(q).getMoteSize()/2;
		rad2=list.get(i).getMoteSize()/2;
		distance=Math.sqrt((num3-num1)*(num3-num1)+(num4-num2)*(num4-num2));
		if (distance > (rad2 + rad1)) 
		{
		    // No overlap
		    
		    return false;
		}
		else if ((distance <= Math.abs(rad2 - rad1))) 
		{
		    // Inside
		    return true;
		}
		else if ((distance <= rad2 + rad1) )
		{
		   // Overlap
		   return true;
		}
		else
			return false;
	}
	
	public void deleteMote() {
		flagSize=size;
		ltr = list.listIterator(); 
		while(ltr.hasNext()) {
			OtherMote o = ltr.next();
			n1=o.moteCoordArea(x+size/2, y+size/2, size/2, q);
			flagSize3=o.getMoteSize();
			//jeigu othermote mazesnis uz mymote
			if(n1==true && o.getMoteSize()<size) {
				o.reduceSize();
				s=o.getMoteSize();
				if(s<2) {
					ltr.remove();
				}
				size=size+0.04;
				flag=false;
				marked=o.isInside();
				//jei othermote viduje mymote, tai othermote iskart praryjamas
				if(marked==false) {
					ltr.remove();
					size=size+s/4;
				}
				break;
			}
			num1=o.getXX()+o.getMoteSize()/2;
			num2=o.getYY()+o.getMoteSize()/2;
			n2=moteCoordArea(num1, num2, o.getMoteSize()/2, q);
			//jeigu othermote didesnis nei mymote
			if(n2==true && o.getMoteSize()>size) {
				reduceSize(size);
				o.increaseSize();
				flag3=false;
			}
			if(flagSize3==o.getMoteSize())
				flag3=true;
			o.sendFlagToOtherMoteY(flag3);
		}
	}
	
	public void checkOtherMote() {
		ltr = list.listIterator();
		while(ltr.hasNext()) {
			q=ltr.nextIndex();
			OtherMote o = ltr.next();
			int i=0;
			for(OtherMote mote: list) {
				n2=moteCoordArea(q, i);
				flagSize2=mote.getMoteSize();
				if(n2==true && o.getMoteSize()<mote.getMoteSize() && q!=i) {
					if(o.getMoteSize()<2) {
						ltr.remove();
						mote.increaseSize();
						i++;
						break;
					}
					flag2=false;
					mote.increaseSize();
					o.reduceSize();
				}
				if(flagSize2==mote.getMoteSize())
					flag2=true;
				mote.sendFlagToOtherMoteX(flag2);
				i++;
			}
		}
	}
	
	//useless
	public void checkFlag() {
		if(flagSize==size)
			flag=true;
	}

	
	public void run() {
			myCoordinates();
	}
	
	public void changeStatus(boolean r) {
		running=r;
		repaint();
	}
	
	public void restartAndQuit() {
		xx = new JPanel();
		xx.setOpaque(false);
		xx.setBorder(new EmptyBorder(new Insets(400, 20, 0, 0)));	//button centravimas
		HandlerClass handler = new HandlerClass();

		restart=new JButton();
		restart.setIcon(new ImageIcon(btn1));							
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		restart.setFocusPainted(false);
		restart.setPressedIcon(new ImageIcon(btnpressed1));
		xx.add(restart);
		
		quit=new JButton();
		quit.setIcon(new ImageIcon(btn2));
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		quit.setFocusPainted(false);
		quit.setPressedIcon(new ImageIcon(btnpressed2));
		xx.add(quit);
		this.add(xx);
		this.revalidate();
	    this.repaint();
		
		quit.addActionListener(handler);
		restart.addActionListener(handler);
		
		flag4=false;
	}
	
	public synchronized void myCoordinates(){
		while(running) {
			x +=tanX*speed;
			y +=tanY*speed;
			if(x < -500 || x>1460-size) {
				tanX = -tanX;
			}
			if(y < -500 || y>1260-size) {
				tanY = -tanY;
			}
			repaint();
				try
			       {
						Thread.sleep(8);
			       }
			       catch(InterruptedException e)
			       {
			          System.out.println("Main thread interrupted");
			       }
				//'game over' arba 'you won' atveju
			if((size<=15 || size>(myMax+20)) && flag4==true ) {
				restartAndQuit();
			}
		}
	}
		
}
