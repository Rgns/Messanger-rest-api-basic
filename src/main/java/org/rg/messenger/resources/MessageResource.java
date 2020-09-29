package org.rg.messenger.resources;

import org.rg.messenger.beans.MessageFilterBean;
import org.rg.messenger.model.Message;
import org.rg.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
        if (messageFilterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(messageFilterBean.getYear());
        }
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() >= 0) {
            return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(id);
        message.addLink(getLinks(String.valueOf(id), uriInfo, MessageResource.class,null,null), "self");
        message.addLink(getLinks(message.getAuthor(), uriInfo, ProfileResource.class,null,null), "profile");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("messageId",id);
        message.addLink(getLinks(null, uriInfo, MessageResource.class, map,"getCommentResource"), "comments");

        return Response.accepted()
                .entity(message)
                .build();
    }

    private String getLinks(String pathparam,
                            UriInfo uriInfo,
                            Class aClass,
                            HashMap<String, Object> resolveTemplateMap,
                            String methodParam) {
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(aClass);
        if (pathparam != null) {
            uriBuilder = uriBuilder.path(pathparam);
        }
        if(methodParam != null){
            uriBuilder = uriBuilder.path(aClass,methodParam);
        }
        if (resolveTemplateMap != null) {
            uriBuilder = uriBuilder.resolveTemplates(resolveTemplateMap);
        }

        return uriBuilder
                .build()
                .toString();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message) {
        Message newMessage = messageService.addMessage(message);
        return Response.status(Response.Status.CREATED)
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeMessage(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }


    @Path("{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }


}
