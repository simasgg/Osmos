import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class OtherMote extends Mote{
	private static final long serialVersionUID = 1L;
	private double rad;
	boolean flagX, flagY, marked, inside;
	
	public OtherMote(double x, double y, double size, int width, int height){
		xx=x+size/2;
		yy=y+size/2;
		this.size=5;
		speed=0.3;
		flagX=true;
		flagY=true;
		marked=true;
		inside=true;
		mythread = new Thread(this);
		mythread.start();
		UploadPhotos();
		try {
			ToCheck(x, y, size);
				} catch (BoundAndSizeException e){
				  e.printStackTrace();
				  //max = 1400/1200, min = -400
				  xx=new Random().nextInt(1400 + 400) + 400;
				  yy=new Random().nextInt(1200 + 400) + 400;
				  this.size=new Random().nextInt(25)  + 10;
				}
		
	}
	public OtherMote(double x, double y, double size){
		xx=x+size/2;
		yy=y+size/2;
		this.size=size;
		UploadPhotos();
		try {
			ToCheck(x, y, size);
				} catch (BoundAndSizeException e){
				  e.printStackTrace();
				  xx=new Random().nextInt(1400 + 400) - 400;
				  yy=new Random().nextInt(1200 + 400) - 400;
				  this.size=new Random().nextInt(25)  + 10;
				}
	}
	
	
	public void ToCheck(double k1, double k2, double s) throws BoundAndSizeException{
		if(k1<-600 || k2<-600 || k1>1560-size || k2>1260-size) {
			throw new BoundAndSizeException("New Mote object is out of bounds: K1:"+k1+"k2: "+k2);
		}
		else if(s<=0)
			throw new BoundAndSizeException("Size of Mote must be positive");
	}
	
	public void UploadPhotos(){
		 try {
			  img = ImageIO.read(new File("Files/images/nOtherMote.png"));
			  img2 = ImageIO.read(new File("Files/images/nOtherMote2.png"));
			  img3 = ImageIO.read(new File("Files/images/OtherMoteChange.png"));
			  img4 = ImageIO.read(new File("Files/images/OtherMoteChange2.png"));
				} catch (IOException e){
				  e.printStackTrace();
				  System.out.println("If you want this program to successfully work "
				  		+ "please put all the images needed for this program into /Files/images directory   ");
				  System.exit(1);
				}
		 }
	
	public double getXX() {
		return xx;
	}
	
	public double getYY() {
		return yy;
	}
	
	//size=this class
	//radius=myclass
	public boolean moteCoordArea(double kX, double kY, double radius, int q) {
		rad=radius*2;
		num1=xx+size/2;
		num2=yy+size/2;
		distance=Math.sqrt((kX-num1)*(kX-num1)+(kY-num2)*(kY-num2));
		if (distance > (radius + size/2)) 
		{
		    // No overlap
			inside=true;
		    marked=false;
		    return false;
		}
		else if ((distance <= Math.abs(radius - size/2)) && (marked==false)) 
		{
		    // Inside
			inside=false;	//<-----
		    return true;
		}
		else if ((distance <= radius + size/2) && (marked==false))
		{
		   // Overlap
		   inside=true;
		   return true;
		}
		else {
			inside=true;
			return false;
		}
	}
	
	public boolean isInside() {
		return inside;
	}
	
	public void reduceSize() {
		size=size-0.2;
	}
	
	public void init(double x, double y, double size) {
		xx=x+size/2;
		yy=y+size/2;
	}
	
	public void polarCoords(double distanceX, double distanceY) {
		angle = Math.atan2(distanceY, distanceX) * 180 / Math.PI;
		tanX = -Math.cos(angle * Math.PI/180) * 2;
		tanY = -Math.sin(angle * Math.PI/180) * 2;
	}
	
	public void increaseSize() {
		size=size+0.05;
	}
	
	//susikuriu OtherMote circles
	public void paintComponent(Graphics gg, Graphics2D g2d) {
		super.paintComponent(gg);
		if(rad<size) {
			if(flagX==false || flagY==false)
				g2d.drawImage(img3, (int)xx, (int)yy, (int)size, (int)size, this);
			else
				g2d.drawImage(img, (int)xx, (int)yy, (int)size, (int)size, this);
		}
		else {
			if(flagX==false || flagY==false)
				g2d.drawImage(img4, (int)xx, (int)yy, (int)size, (int)size, this);
			else
				g2d.drawImage(img2, (int)xx, (int)yy, (int)size, (int)size, this);
		}
	}
	
	public void sendFlagToOtherMoteX(boolean x){
		flagX=x;
	}
	
	public void sendFlagToOtherMoteY(boolean y){
		flagY=y;
	}

	
	public void run() {
			myCoordinates();
	}
	
	public synchronized void myCoordinates(){
		while(!Thread.currentThread().isInterrupted()) {
			xx -=tanX*speed;
			yy -=tanY*speed;
			if(xx < -500 || xx>1460-size) {
				tanX = -tanX;
			}
			if(yy < -500 || yy>1260-size) {
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
			}
	}
}
