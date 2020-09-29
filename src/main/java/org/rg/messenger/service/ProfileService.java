package org.rg.messenger.service;

import org.rg.messenger.database.DatabaseClass;
import org.rg.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private final Map<String, Profile> profiles = DatabaseClass.getProfileMap();

    public ProfileService() {
        Profile p1 = new Profile(1L,"Rg","Ritu", "Gupta");
        Profile p2 = new Profile(2L,"Ak","Akash", "Kumar");
        profiles.put(p1.getProfileName(),p1);
        profiles.put(p2.getProfileName(),p2);
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public void removeProfile(String profileName) {
        profiles.remove(profileName);
    }
}
