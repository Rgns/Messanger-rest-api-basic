package org.rg.messenger.database;

import org.rg.messenger.model.Comment;
import org.rg.messenger.model.Message;
import org.rg.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static final Map<Long, Message> messageMap = new HashMap<>();
    private static final Map<String, Profile> profileMap = new HashMap<>();

    public static Map<Long, Message> getMessageMap() {
        return messageMap;
    }

    public static Map<String, Profile> getProfileMap() {
        return profileMap;
    }


}
