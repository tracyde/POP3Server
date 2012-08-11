package org.twistedcode.ssw810.pop3;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 7:45 AM
 * To change this template use File | Settings | File Templates.
 */
public interface State {
    void writeName(StateContext stateContext, String name);

    String parseCommand(StateContext stateContext, String command);
}
