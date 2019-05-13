package dev.mlqs.myblog.dao;

import java.util.List;

import dev.mlqs.myblog.model.Comment;

public interface CommentDao {

	boolean deleteComment(int comment_id);
	boolean addComment(Comment comment);
	List getComment(int article_id);
}
