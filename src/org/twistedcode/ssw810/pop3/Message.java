package org.twistedcode.ssw810.pop3;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message implements Serializable {
    private Integer byteSize;
    private String body;
    private Boolean delete;
    private Long lastAccess; // long epoch = System.currentTimeMillis()/1000;

    public Message(String body) {
        this.body = body;
        this.byteSize = body.getBytes().length;
        this.delete = false;
        this.lastAccess = System.currentTimeMillis() / 1000;
    }

    public Integer getByteSize() {
        return this.byteSize;
    }

    public void setDelete(Boolean bool) {
        this.delete = bool;
    }
}
