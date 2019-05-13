package dev.mlqs.myblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mlqs.myblog.dao.TagDao;
import dev.mlqs.myblog.daoImpl.TagDaoImpl;
import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.model.Tag;
import dev.mlqs.myblog.utils.ArticleUtils;
import dev.mlqs.myblog.utils.StringUtils;


public class TagService {

	private TagDao dao;

	private static TagService instance;

	private TagService() {
		dao = TagDaoImpl.getInstance();
	}

	public static final TagService getInstance() {
		if (instance == null) {
			try {
				instance = new TagService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public List getTagById(String id) {
		return dao.getTagByColumn("id", id);
	}

	public List getAllTag() {
		return dao.getAllTag();
	}

	public int getTagCount() {
		return dao.getAllTag().size();
	}

	
	public Map getTagAndArticle(String tag_name) {

		ArticleService as = ArticleService.getInstance();
		Map map = new HashMap();

		List<Tag> tag_list;
		if (tag_name.equals("all") || StringUtils.isEmpty(tag_name)) {
			
			tag_list = dao.getAllTag();
		} else {
			
			tag_list = dao.getTagByColumn("tag", tag_name);
		}

		
		List<Article> article_list = null;
		
		for (Tag tag : tag_list) {
			List<Tag> list = dao.getTagByColumn("tag", tag.getTag());
			article_list = new ArrayList();
			
			for (Tag tag_all : list) {
				
				List<Article> result = as.getArticle("id", String.valueOf(tag_all.getId()));
				article_list.add(ArticleUtils.cutTime(result.get(0)));
			}
			
			map.put(tag.getTag(), article_list);
		}
		return map;
	}
}
