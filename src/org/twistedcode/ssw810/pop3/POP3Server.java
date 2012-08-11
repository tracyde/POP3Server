package org.twistedcode.ssw810.pop3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/10/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class POP3Server {
    // TODO change PORT back to 110 before submitting for grade
    public final static Integer PORT = 11000;

    public POP3Server() {
        ServerSocket listenSocket = null;

        try {
            listenSocket = new ServerSocket(PORT);
        } catch (IOException ioe) {
            System.out.println("Could not create server socket at " + PORT + ". Quitting.");
            ioe.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Listening for clients on " + PORT + "...");

        int id = 0;
        while (true) {
            try {
                // Accept incoming client connections
                Socket clientSocket = listenSocket.accept();

                // accept() will block until a client connects to the server.
                ClientServiceThread clientThread = new ClientServiceThread(clientSocket, id++);
                clientThread.start();
            } catch (IOException ioe) {
                System.out.println("Exception encountered on accept. Ignoring. Stack Trace :");
                ioe.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new POP3Server();
    }
}
