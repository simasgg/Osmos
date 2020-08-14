import java.awt.event.MouseWheelEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class CameraAndAudio {
	private double zoom, anchorx, anchory;
	File musicPath;
	
	public CameraAndAudio(double data) {
		this.zoom=data;
	}
	
	public CameraAndAudio() {
		musicPath = null;
	}
	
	public void ZoomInAndOut(MouseWheelEvent e){
		if(zoom >= 0.8 && zoom <= 2.00) {
            if (e.getWheelRotation() >= 0)
            {
                //System.out.println("mouse wheel Up"+zoom);
                zoom -= 0.05;
            }
            else
            {
                //System.out.println("mouse wheel Down"+zoom);
                zoom += 0.05;
            }
        }else if(zoom > 2.00){
        	if (e.getWheelRotation() > 0)
            {
                //System.out.println("mouse wheel Up"+zoom);
                zoom -= 0.05;
            }
        }else {
        	if (e.getWheelRotation() < 0)
            {
                //System.out.println("mouse wheel Down"+zoom);
                zoom += 0.05;
            }
        }
	}
	
	public double getZoom(){
		return zoom;
	}
	
	public void calculate(double x, double y) {
		double width = x;
        double height = y;

        double zoomWidth = width * zoom;
        double zoomHeight = height * zoom;

        anchorx = (width - zoomWidth) / 2;
        anchory = (height - zoomHeight) / 2;
	}
	
	public double getAnchorX() {
		return anchorx;
	}
	
	public double getAnchorY() {
		return anchory;
	}
	
	public void playMusic() {
		uploadMusic();
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
			e.printStackTrace();
			  System.out.println("Error playing music");
		}
	}
	
	public void uploadMusic() {
		try {
			musicPath = new File("Files/music/Sickbay.wav");
		}catch (Exception e){
		   e.printStackTrace();
		   System.out.println("If you want this program to successfully work "
		 	  		+ "please put all the music needed for this program into /Files/music directory   ");
		   System.exit(1);
		  }
	}
}
