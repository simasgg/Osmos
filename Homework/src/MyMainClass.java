
public class MyMainClass {
	public static void main(String args[]) {
		Runnable r = () -> {
			MyFrame frame = new MyFrame();
			Mote m = new MyMote(); 
			Thread mythread = new Thread(m);
		    mythread.start();
			m.setSize(1000,800);
			frame.addComponent(m);
		};
		r.run();
		}

}
