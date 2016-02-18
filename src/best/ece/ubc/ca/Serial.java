package best.ece.ubc.ca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;

import synths.Instrument;
import synths.Piano;


public class Serial implements SerialPortEventListener {
	private SerialPort serialPort;
	private String inputLine;
	private boolean newData = false;
	private static final String PORT_NAMES[] = { "COM3" };
	//	"/dev/ttyACM0", // Linux

	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	private NoteParser parser;
	private Instrument musicMaker;

	public Serial() {
	    // the next line is for Raspberry Pi and 
	    // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
	    //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
	    System.setProperty("gnu.io.rxtx.SerialPorts", "COM3");
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		musicMaker = new Piano();
		try {
			parser = new NoteParser("notes.cfg");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				System.out.println(currPortId.getName());
				System.out.println(portName);
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				inputLine=input.readLine();
				newData = true;
				System.out.println(inputLine); // DEBUGGING
				String note = inputLine;
					try {
						note = parser.getNote(inputLine);
						System.out.println("cat" + note);
						musicMaker.play(note);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			 catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// TODO Consider the other event types
	}
	
	/*
	 * Return lastline if new data, else null;
	 */
	public String readLastLine() {
		//return inputLine;
		if(newData) {
			newData = false;
			return inputLine;
		}
		else {
			return null;
		}
	}
}
