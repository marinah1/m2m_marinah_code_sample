package best.ece.ubc.ca;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NoteParser {
    private HashMap<String, String> map = new HashMap<String, String>();

	NoteParser(String filename) throws IOException{
	    FileReader input = new FileReader(filename);
	    BufferedReader bufRead = new BufferedReader(input);
	    String myLine = null;

	    String[] tempArray;

	    while ( (myLine = bufRead.readLine()) != null) 
	    {
	        tempArray = myLine.split(",");
	        if(!map.containsKey(tempArray[0])) {
	            map.put(tempArray[0].replaceAll("\\s", ""), tempArray[1].replaceAll("\\s", ""));
	        }
	    }
	    bufRead.close();
	}
	
	public String getNote(String input) throws Exception {
		if (map.containsKey(input)) {
			return map.get(input);
		}
		throw new Exception("No note mapping");
	}
}

