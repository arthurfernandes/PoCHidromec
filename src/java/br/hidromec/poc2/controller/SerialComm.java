package br.hidromec.poc2.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialComm {
    static String serial_input_file = "/root/serialfiles/serial_input.txt";
    static String output_buffer_file = "/root/serialfiles/serial_output.txt";
    static String cmd = "/bin/write_serial ";

    static String getInputBuffer() {
        String inputBuffer = "";
        Path file = Paths.get(serial_input_file);

        // Open file and convert to string
        try (BufferedReader reader =
                Files.newBufferedReader(file, Charset.forName("US-ASCII"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                inputBuffer += line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
        return inputBuffer;
    }

    public void write(String send) {
      // Convert the string to a
      // byte array.
      byte data[] = send.getBytes();
      Path p = Paths.get(output_buffer_file);

      try (OutputStream out = new BufferedOutputStream(
       Files.newOutputStream(p, CREATE, TRUNCATE_EXISTING))) {
        out.write(data, 0, data.length);

        Runtime.getRuntime().exec(cmd + p.toString());
      } catch (IOException x) {
        System.err.println(x);
      }
    }
    
    public void write(Integer send) {
        write(Integer.toString(send));
    }
    
    static boolean clearBuffer(){
        try {
            // Removing file and creating again
            Runtime.getRuntime().exec("/bin/rm " + serial_input_file);
            Runtime.getRuntime().exec("/bin/touch " + serial_input_file);
        } catch (IOException ex) {
            Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
}
