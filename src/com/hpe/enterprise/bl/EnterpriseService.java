package com.hpe.enterprise.bl;

import java.util.List;

import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;
import com.hpe.enterprise.models.CommentsWithReplies;

public interface EnterpriseService {

	public long postComment(Comment comment) throws Exception;

	public long replyOnComment(Reply reply) throws Exception;

	public List<CommentsWithReplies> getCommentsWithReplies(long orderId) throws Exception;

}
