package synths;

public interface Instrument {

	public void config();
	
	public void play(String note);
	
	public void close();
}
