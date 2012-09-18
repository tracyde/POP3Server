package org.twistedcode.ssw810.pop3;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 7:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class StateContext {
    private State myState;
    private Boolean runnable = true;
    private MessageContainer messageContainer;

    public StateContext() {
        // Set initial state to AUTHORIZATION
        messageContainer = new MessageContainer();
        setState(new StateAuthorization());
    }

    public void setState(State newState) {
        this.myState = newState;
    }

    public void writeName(String name) {
        this.myState.writeName(this, name);
    }

    public String parseCommand(String command) {
        return this.myState.parseCommand(this, command);
    }

    public Boolean isRunnable() {
        return runnable;
    }

    public void setRunnable(Boolean bool) {
        runnable = bool;
    }

    public void setMessageContainer(MessageContainer messageContainer) {
        this.messageContainer = messageContainer;
    }
}
