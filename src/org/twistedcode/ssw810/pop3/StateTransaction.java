package org.twistedcode.ssw810.pop3;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 7:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class StateTransaction implements State {
    @Override
    public void writeName(StateContext stateContext, String name) {
        System.out.println(name.toLowerCase());
    }

    @Override
    public String parseCommand(StateContext stateContext, String command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
