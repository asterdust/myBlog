package dev.mlqs.myblog.dao;

import java.util.List;

public interface TagDao {

	
	
	
	int DEFAULT_ID = 0;
	String DEFAULT_TAG = "LEMONREDS2017";

	
	boolean addTag(int id, String tag);

	
	boolean deleteTag(int id, String tag);

	
	List getAllTag();

	
	boolean updateTag(String old_tag, String new_tag);

	
	List getTagByColumn(String column, String value);

}
