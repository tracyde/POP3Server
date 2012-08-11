package org.twistedcode.ssw810.pop3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractState implements State {
    protected final static String OK = "+OK ";
    protected final static String ERR = "-ERR ";
    protected final static String CRLF = "\r\n";

    // The valid commands in this state
    protected final List<String> validCommands = new ArrayList<String>();

    public AbstractState() {
        addValidCommand("quit");
    }

    public void addValidCommand(String command) {
        this.validCommands.add(command.toLowerCase());
    }

    @Override
    public void writeName(StateContext stateContext, String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parseCommand(StateContext stateContext, String command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
