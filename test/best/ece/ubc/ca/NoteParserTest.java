package best.ece.ubc.ca;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class NoteParserTest {
	NoteParser parser;
	
	@Before
	public void setUp() {
		try {
			parser = new NoteParser("notes.cfg");
		} catch (IOException e) {
			fail("Constructor threw an error");
		}
	}

	@Test
	public void getNoteSucceed() {
		String note;
		try {
			note = parser.getNote("G_UP");
			assertEquals(note, "A");
			note = parser.getNote("P_0");
			assertEquals(note, "F");
			note = parser.getNote("G_DOWN");
			assertEquals(note, "B");
			note = parser.getNote("G_RIGHT");
			assertEquals(note, "D");
			note = parser.getNote("T_3");
			assertEquals(note, "A#");
		} catch (Exception e) {
			fail("Failed to parse G_UP, P_0, G_DOWN, G_RIGHT, T_3");
		}
	}
	
	@Test
	public void makeFail() {
		try {
			String note = parser.getNote("G_HAPPY");
			fail("Failed to throw exception parsing string that does not exist");
		} catch (Exception e) {
		}
	}
}
