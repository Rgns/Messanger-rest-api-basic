package org.rg.messenger.service;

import org.rg.messenger.database.DatabaseClass;
import org.rg.messenger.exception.DataNotFoundException;
import org.rg.messenger.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {

    private final Map<Long, Message> messages = DatabaseClass.getMessageMap();

    public MessageService() {
        Message m1 = new Message(1L, "you have a message", "Ritu");
        Message m2 = new Message(2L, "you have a second message", "Akash");
        messages.put(m1.getId(), m1);
        messages.put(m2.getId(), m2);
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<Message>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year)
                messagesForYear.add(message);
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {

        List<Message> messageList = new ArrayList<Message>(messages.values());
        return messageList.subList(start, start + size);
    }

    public Message getMessage(Long id) {
        Message message = messages.get(id);
        if (message == null){
            throw new DataNotFoundException("Message not found");
        }
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public void removeMessage(long id) {
        messages.remove(id);
    }
}
