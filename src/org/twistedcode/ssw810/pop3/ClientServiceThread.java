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
public class ClientServiceThread extends Thread {
    private final static String OK = "+OK ";
    private final static String ERR = "-ERR ";
    private final static String CRLF = "\r\n";

    Socket m_clientSocket;
    int m_clientID = -1;
    //boolean m_bRunThread = true;

    ClientServiceThread(Socket s, int clientID) {
        m_clientSocket = s;
        m_clientID = clientID;
        //m_clientSocket.getInetAddress().getHostName();
    }

    public void run() {
        String response;

        // Obtain the input stream and the output stream for the socket
        BufferedReader in = null;
        PrintWriter out = null;

        // Print out details of this connection
        System.out.println("Accepted Client : ID - " + m_clientID + " : Address - " +
                m_clientSocket.getInetAddress().getHostName());

        try {
            in = new BufferedReader(new InputStreamReader(m_clientSocket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(m_clientSocket.getOutputStream()));

            // Set response to the initial greeting and let client know we are ready
            response = OK + "POP3 Server Ready" + CRLF;
            sendResponse(out, response);

            // Setup new StateContext to hold thread state
            StateContext sc = new StateContext();

            // Run in a loop until m_bRunThread is set to false
            while (sc.isRunnable()) {
                // read incoming stream
                // TODO need to change this to have parseCommand add commands to a queue which are then popped off for execution
                response = sc.parseCommand(in.readLine());
                sendResponse(out, response);
            }

            // Once outside the loop the thread will start closing
            sendResponse(out, OK + "Server closing connection" + CRLF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                System.out.print("Stopping client thread for client : " + m_clientID);
                in.close();
                out.close();
                m_clientSocket.close();
                System.out.println("...Client Thread Stopped");
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            } catch (NullPointerException npe) {
                System.err.println(npe.getMessage());
            }
        }
    }

    // The response sent to sendResponse must contain \r\n
    private void sendResponse(PrintWriter printWriter, String resp) {
        printWriter.print(resp);
        printWriter.flush();
    }
}
