package org.rg.messenger.service;

import org.rg.messenger.database.DatabaseClass;
import org.rg.messenger.model.Comment;
import org.rg.messenger.model.ErrorMessage;
import org.rg.messenger.model.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {
    private final Map<Long, Message> messageMap = DatabaseClass.getMessageMap();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> commentMap = getCommentMap(messageId);
        return new ArrayList<Comment>(commentMap.values());
    }

    public Comment addComment(long messageId, Comment comment) {
        comment.setId(getCommentMap(messageId).size() + 1);
        getCommentMap(messageId).put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, long commentId, Comment comment) {
        if (messageId > 0 && commentId > 0) {
            getCommentMap(messageId).put(commentId, comment);
            return comment;
        }
        return null;
    }

    public void removeComment(long messageId, long id) {

        if (getCommentMap(messageId).get(id) == null) {
            // another way of throwing the exception, its not business code so not preferred , so better to have separate Mapper class
            ErrorMessage errorMessage = new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(),
                    "https://github.com/Rgns",
                    "Id to be deleted not present");

            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity(errorMessage)
                            .build()
            );
        }
        getCommentMap(messageId).remove(id);
    }

    private Map<Long, Comment> getCommentMap(long messageId) {
        return messageMap.get(messageId).getComments();
    }

}
