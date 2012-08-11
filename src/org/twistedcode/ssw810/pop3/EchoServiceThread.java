package org.twistedcode.ssw810.pop3;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/10/12
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoServiceThread extends Thread {
    Socket m_clientSocket;
    int m_clientID = -1;
    boolean m_bRunThread = true;

    EchoServiceThread(Socket s, int clientID) {
        m_clientSocket = s;
        m_clientID = clientID;
    }

    public void run() {
        // Obtain the input stream and the output stream for the socket
        // A good practice is to encapsulate them with a BufferedReader
        // and a PrintWriter as shown below.
        BufferedReader in = null;
        PrintWriter out = null;

        // Print out details of this connection
        System.out.println("Accepted Client : ID - " + m_clientID + " : Address - " +
                m_clientSocket.getInetAddress().getHostName());

        try {
            in = new BufferedReader(new InputStreamReader(m_clientSocket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(m_clientSocket.getOutputStream()));

            // At this point, we can read for input and reply with appropriate output.

            // Run in a loop until m_bRunThread is set to false
            while (m_bRunThread) {
                // read incoming stream
                String clientCommand = in.readLine();

                System.out.println("Client Says :" + clientCommand);


                if (clientCommand.equalsIgnoreCase("quit")) {
                    // Special command. Quit this thread
                    m_bRunThread = false;
                    System.out.print("Stopping client thread for client : " + m_clientID);
                } else {
                    // Echo it back to the client.
                    out.println(clientCommand);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                in.close();
                out.close();
                m_clientSocket.close();
                System.out.println("...Stopped");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
