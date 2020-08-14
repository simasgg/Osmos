
public interface Playable {
	// metodas atsakingas uz sarysi tarp 2 Mote
	public boolean moteCoordArea(double kX, double kY, double radius, int q);
	
	// metodas atsakingas uz MyMote ir OtherMote fizini judejima
	public void polarCoords(double distanceX, double distanceY);
	
	// metodas atsakingas uz MyMote ir/arba OtherMote susitraukima, ivykus abieju Mote kolizijai
	public void reduceSize();
	
	public void increaseSize();
}
