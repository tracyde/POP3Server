package org.twistedcode.ssw810.pop3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class StateAuthorization extends AbstractState implements State {
    // Variables to hold username/password of client attempting to be authorized
    private String userName = null;
    private String password = null;


    // Since we are allowed to hardcode password, doing this initially
    // TODO implement a better password checking routine
    private final Map<String, String> validUsers = new HashMap<String, String>();

    // Set flag to hold decision about user validity
    private Boolean validUser = false;

    // The total and max number of times user attempted to login
    private int attempts = 0;
    private final int maxAttempts = 3;

    public StateAuthorization() {
        addValidCommand("user");
        addValidCommand("pass");
        addValidCommand("quit");
        this.validUsers.put("testuser1", "abcd1234");
        this.validUsers.put("testuser2", "abcd1234");
    }

    @Override
    public void writeName(StateContext stateContext, String name) {
        System.out.println(name.toLowerCase());
        stateContext.setState(new StateTransaction());
    }

    @Override
    public String parseCommand(StateContext stateContext, String command) {
        // delims are the fields delimeters used in parsing commands
        final String delims = "[ ]+";
        String response = "";
        String[] tokens = null;

        // Ignore commands that contain nothing
        if (!command.isEmpty()) {
            tokens = command.split(delims);
        }

        if (tokens.length > 0) {
            if (validCommands.contains(tokens[0].toLowerCase())) {
                String s = tokens[0].toLowerCase();
                if (s.equals("user")) {
                    setUsername(tokens);
                    if (this.userName.isEmpty()) {
                        response = ERR + "Username was not sent" + CRLF;
                    } else {
                        response = OK + this.userName + CRLF;
                    }
                } else if (s.equals("pass")) {
                    setPassword(tokens);
                    if (!this.password.isEmpty()) {
                        setValidUser();
                        if (this.validUser) {
                            response = OK + "Welcome " + this.userName + CRLF;
                        } else {
                            attempts++;
                            response = ERR + "Invalid username/password combination " + Integer.toString(maxAttempts - attempts) + " attempts left" + CRLF;
                        }
                    } else {
                        response = ERR + "Must send password" + CRLF;
                    }
                } else if (s.equals("quit")) {
                    response = OK + "Server closing connection" + CRLF;
                    closeConnection(stateContext);
                }
            } else {
                response = ERR + "Unknown command" + CRLF;
            }
        }
        return response;
    }

    private void setUsername(String[] tokens) {
        if (tokens.length >= 2) {
            this.userName = tokens[1];
        }
    }

    private void setPassword(String[] tokens) {
        this.password = "";
        if (tokens.length >= 2) {
            for (int i = 1; i < tokens.length; i++) {
                this.password += tokens[i];
            }
        }
    }

    private void setValidUser() {
        if (validUsers.containsKey(this.userName)) {
            if (validUsers.get(this.userName).equals(this.password)) {
                this.validUser = true;
            }
        }
    }

    private void closeConnection(StateContext stateContext) {
        stateContext.setRunnable(false);
    }
}
