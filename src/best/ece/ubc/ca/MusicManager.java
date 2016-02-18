package best.ece.ubc.ca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import synths.Instrument;
import synths.Piano;
import synths.ThreadPlayer;

public class MusicManager{

		public void start() throws IOException {
			Serial serial = new Serial();
//			Instrument musicMaker = new Piano();
//			NoteParser parser = new NoteParser("notes.cfg");
			
//		    FileReader input = new FileReader("notes.cfg");
//		    BufferedReader bufRead = new BufferedReader(input);
//		    String myLine = null;
//
//		    String[] tempArray;
//
//		    while ( (myLine = bufRead.readLine()) != null)
//		    {
//		        tempArray = myLine.split(",");
//		        String testString = tempArray[0].replaceAll("\\s", "");
//		        System.out.println(testString);
//		        
//				try {
//					String note = parser.getNote(testString);
//					musicMaker.play(note);
//			//		ThreadPlayer threadPlayer = new ThreadPlayer();
//				//	threadPlayer.start(note);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//		    bufRead.close();
			
//			while(true) {
//				String serialInput = serial.readLastLine();
//				String note;
//				if(serialInput != null) {
//					System.out.println("cat" + serialInput);
//					try {
//						note = parser.getNote(serialInput);
//						System.out.println("cat" + note);
//						musicMaker.play(note);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
			
		}
}
