package org.rg.messenger.resources;

import org.rg.messenger.model.Comment;
import org.rg.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    CommentService commentService = new CommentService();

    @GET
    public List<Comment> getComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId,
                              Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId,
                                 Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, commentId, comment);
    }

    @DELETE
    @Path("{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        commentService.removeComment(messageId, commentId);
    }


}
