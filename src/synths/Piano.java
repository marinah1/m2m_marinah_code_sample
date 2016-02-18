package synths;

import org.jfugue.player.Player;


public class Piano implements Instrument{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(String note) {
		// TODO ADD threads if needed
		
		Player player = new Player();
		//player.play(note);
		player.delayPlay(0, note);
	    //player.delayPlay(1, "D C ");
	    //player.play("D");
//		try {
//			Thread.sleep(0);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}


