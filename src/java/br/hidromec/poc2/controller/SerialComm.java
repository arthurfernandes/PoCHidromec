package br.hidromec.poc2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.CommPort;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class SerialComm {
    static String inputBuffer = "";

    static String getInputBuffer() {
        return inputBuffer;
    }
    OutputStream serialOutputstream;
    static SerialPort serialPort;
    int DATA_RATE = 5000000;
    int TIME_OUT = 2000;

    void connect(String portName) {
	// the next line is for Raspberry Pi and 
        // gets us into the while loop and was suggested
        // here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", portName);

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId
                    = (CommPortIdentifier) portEnum.nextElement();
            System.out.println("port name: " + currPortId.getName());
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }
        if (portId.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort;

            try {
                commPort = portId.open(this.getClass().getName(), TIME_OUT);

                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(DATA_RATE,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);

                    InputStream in = serialPort.getInputStream();
                    OutputStream out = serialPort.getOutputStream();
                    serialOutputstream = out;

                    (new Thread(new SerialReader(in))).start();
                    //( new Thread( new SerialWriter( out ) ) ).start();

                } else {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            } catch (PortInUseException e) {
                // TODO Auto-generated catch block
                System.err.println(e.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println(e.toString());
            } catch (UnsupportedCommOperationException e) {
                // TODO Auto-generated catch block
                System.err.println(e.toString());
            }
        }
    }

    public static class SerialReader implements Runnable {

        InputStream in;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int len = -1;
            try {
                while ((len = this.in.read(buffer)) > -1) {
                    System.out.print(new String(buffer, 0, len));
                }
            } catch (IOException e) {
                System.out.println("Problem in run function at SerialReader");
            }
        }
    }

    public static class SerialWriter implements Runnable {

        OutputStream out;

        public SerialWriter(OutputStream out) {
            this.out = out;
        }

        public void run() {
            try {
                int c = 0;
		while( ( c = System.in.read() ) > -1 ) {
            	  inputBuffer += Integer.toString(c);
                }
            } catch (IOException e) {
                System.out.println("Problem in run function at SerialWriter");
            }
        }
    }

    public void write(String send) {
        try {
            serialOutputstream.write(send.getBytes());
            serialOutputstream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem in write function at SerialComm");
        }
    }
    
    public void write(Integer send) {
        try {
            serialOutputstream.write(send);
            serialOutputstream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem in write function at SerialComm");
        }
    }

    public void close() {
        try {
            serialOutputstream.close();
            if (serialPort != null) {
                serialPort.removeEventListener();
                serialPort.close();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem at close function");
        }
    }
    
    static boolean clearBuffer(){
        inputBuffer = "";
        
        return true;
    }
}
