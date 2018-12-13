package dev.mlqs.myblog.utils;

import dev.mlqs.myblog.db.VisitorDB;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class SideInfoUtils {

    public static void setUp (HttpServletRequest request, Object _this) {

        // 初始化分类
        ArticleService as = ArticleService.getInstance();
        // 初始化获取标签
        TagService ts = TagService.getInstance();
        request.setAttribute("tag_list", ts.getAllTag());

        // 初始化侧边栏 日志、分类、标签的个数
        // request.setAttribute("article_number", as.getCount(ArticleDao.SEARCH_ARTICLE));
        // request.setAttribute("sort_number", as.getCount(ArticleDao.SEARCH_SORT));
        // request.setAttribute("tags_number", ts.getTagCount());

        // 阅读排行
        request.setAttribute("visit_rank", as.getVisitRank());

        // 传网站的统计数据
        request.setAttribute("visited", VisitorDB.totalVisit());
        request.setAttribute("member", VisitorDB.totalMember());

        // 读取主页信息
        for (Map.Entry<String, String[]> entry : ConfigUtils.getConfigUtils(_this).des_map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[2]);
        }
    }


}
