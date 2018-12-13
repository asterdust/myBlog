package dev.mlqs.myblog.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.model.Comment;

public class Form2Bean {

    public static Comment commentForm2Bean(HttpServletRequest request) throws FailException {

        int id = Integer.valueOf(request.getParameter("id"));

        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String content = request.getParameter("content");

        Comment bean = new Comment();
        bean.setArticle_id(id);
        bean.setNickname(nickname);
        bean.setEmail(email);
        bean.setEmail_hash(GravatarUtils.md5Hex(email));
        bean.setWebsite(website);
        bean.setContent(content);
        if (vilidate(bean)) {
            bean.setTime(DateUtils.getFormatDate(new Date()));
            return bean;
        }
        throw new FailException("Create Fail!");

    }

    private static boolean vilidate(Comment c) {
        boolean result = true;

        if (c.getArticle_id() == 0 || StringUtils.isEmpty(c.getContent())) {
            result = false;
        }
        return result;
    }

    public static Article articleForm2Bean(HttpServletRequest request) throws FailException {

        Map value = new HashMap();

        value.put("title", request.getParameter("title"));
        value.put("time", request.getParameter("time"));
        value.put("author", request.getParameter("author"));
        value.put("sort", request.getParameter("sort"));
        // String tags = request.getParameter("tags");
        value.put("content", request.getParameter("content"));
        value.put("star", 0);
        value.put("comment", 0);
        value.put("visit", 0);

        Article bean = new Article();

        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (vilidate(bean)) {
            return bean;
        }
        throw new FailException("Create Fail!");

    }

    private static boolean vilidate(Article a) {
        boolean result = true;

        if (a.getSort() == null || a.getTitle() == null || a.getAuthor() == null || a.getTime() == null) {
            result = false;
        }

        return result;
    }

}
