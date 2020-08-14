import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

abstract public class Mote extends JPanel implements Playable, Runnable{
	private static final long serialVersionUID = 1L;
	//Timer t = new Timer(5, this);
	protected double prevX, prevY, distanceX, distanceY, angle, tanX, tanY, xx, yy, speed, x , y, num1, num2, distance, s, size;
	Image img, img2, img3, img4;
	protected Thread mythread;
	
	public Mote() {
		
	}
	
	public abstract void init(double x, double y, double size);
		
	public abstract void myCoordinates();
	
	public double getMoteSize() {
		return size;
	}
	
	public abstract double getXX();
	
	public abstract double getYY();
	
	public abstract void UploadPhotos();
	
	public abstract void run();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}
