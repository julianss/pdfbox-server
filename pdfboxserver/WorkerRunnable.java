package pdfboxserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int read;
            for(int i = 0; (read = input.read()) > 0; i++){
                buffer[i] = (byte)read;
            }
            String filename = new String( buffer, Charset.forName("UTF-8") );
            filename = filename.trim();
            System.out.println("Recieved " + filename);
            PDFBoxParser parser = new PDFBoxParser();
            parser.setFilePath(filename);
            String text = parser.ToText();
            System.out.println("Finished parsing");
            OutputStream output = clientSocket.getOutputStream();
            output.write( text.getBytes(Charset.forName("UTF-8")) );
            output.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
