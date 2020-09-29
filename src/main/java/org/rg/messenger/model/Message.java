package org.rg.messenger.model;

import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

public class Message {

    private long id;
    private String userId;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();
    private List<Profile> whoLiked;

    public Message() {
    }

    public Message(long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel){
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);

    }


    public List<Profile> getWhoLiked() {
        return whoLiked;
    }

    public void setWhoLiked(List<Profile> whoLiked) {
        this.whoLiked = whoLiked;
    }

}
