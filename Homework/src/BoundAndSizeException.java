public class BoundAndSizeException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BoundAndSizeException() {
		super();
	}
	
	public BoundAndSizeException(String message) {
		super(message);
	}
	
	public BoundAndSizeException(String message, Throwable cause) {
		super(message, cause);
	}
}
