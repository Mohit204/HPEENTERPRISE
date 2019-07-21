package com.hpe.enterprise.endpoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hpe.enterprise.bl.EnterpriseService;
import com.hpe.enterprise.bl.impl.EnterpriseServiceImpl;
import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;
import com.hpe.enterprise.models.CommentsWithReplies;

@Path("/hpe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnterpriseEndPoints {

	private EnterpriseService enterpriseService = new EnterpriseServiceImpl();

	@POST
	@Path("/post/comment/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postComment(Comment comment) throws Exception {

		Response response = null;

		if (comment == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Comment cannot be empty").build();
		}

		if (comment != null && comment.getOrderId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Order id is not valid").build();
		}

		if (comment != null && comment.getUserId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("User id is not valid").build();
		}

		if (comment != null && comment.getText() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Text section is missing ").build();
		}

		long result = enterpriseService.postComment(comment);
		return Response.status(201).entity("CommentId : " + result).build();

	}

	@POST
	@Path("/reply/comment/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replyOnComment(Reply reply) throws Exception {

		if (reply == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Reply  cannot be empty").build();
		}

		if (reply == null && reply.getCommentId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Reply is missing commentId on which reply is made").build();
		}

		if (reply == null && reply.getText() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Reply  Text section is missing").build();
		}

		if (reply == null && reply.getUserId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("userId who made reply is missing ").build();
		}

		long result = enterpriseService.replyOnComment(reply);
		return Response.status(201).entity("ReplyId : " + result).build();

	}

	@GET
	@Path("/allCommentsWithReplies/{orderId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllCommentsWithReplies(@PathParam("orderId") long orderId) throws Exception {

		if (orderId <= 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("OrderId is not proper ").build();
		}
 
		List<CommentsWithReplies> result = enterpriseService.getCommentsWithReplies(orderId);
		return Response.status(201).entity(result).build();

	}

}
