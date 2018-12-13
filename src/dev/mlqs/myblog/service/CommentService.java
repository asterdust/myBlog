package dev.mlqs.myblog.service;

import java.util.List;

import dev.mlqs.myblog.dao.CommentDao;
import dev.mlqs.myblog.daoImpl.CommentDaoImpl;
import dev.mlqs.myblog.model.Comment;

public class CommentService {

	private CommentDao dao;

	private static CommentService instance;

	private CommentService() {
		dao = CommentDaoImpl.getInstance();
	}

	public static final CommentService getInstance() {
		if (instance == null) {
			try {
				instance = new CommentService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public List loadComment(int article_id) {
		return dao.getComment(article_id);
	}

	public boolean addComment(Comment comment) {
		return dao.addComment(comment);
	}

	public boolean deleteComment(int id) {
		return dao.deleteComment(id);
	}
}
