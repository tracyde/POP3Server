package org.twistedcode.ssw810.pop3;

import java.io.Serializable;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: tracyde
 * Date: 8/11/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageContainer implements Serializable {
    private final SortedMap<Integer, Message> messages;

    public MessageContainer() {
        this.messages = new TreeMap<Integer, Message>();
    }

    public void addMessage(Message message) {
        Integer id = this.messages.lastKey() + 1;
        this.messages.put(id, message);
    }

    public Integer getMessageCount() {
        return this.messages.size();
    }

    public Integer getByteSize() {
        Integer byteSize = 0;
        for (Message m : messages.values()) {
            byteSize += m.getByteSize();
        }
        return byteSize;
    }

    public void deleteMessage(Integer msgId) {
        messages.get(msgId).setDelete(true);
    }
}
