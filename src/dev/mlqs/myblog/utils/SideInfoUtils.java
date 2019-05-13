package dev.mlqs.myblog.utils;

import dev.mlqs.myblog.db.VisitorDB;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class SideInfoUtils {

    public static void setUp (HttpServletRequest request, Object _this) {
        ArticleService as = ArticleService.getInstance();
        TagService ts = TagService.getInstance();
        request.setAttribute("tag_list", ts.getAllTag());
        request.setAttribute("visit_rank", as.getVisitRank());
        request.setAttribute("visited", VisitorDB.totalVisit());
        request.setAttribute("member", VisitorDB.totalMember());
        
        for (Map.Entry<String, String[]> entry : ConfigUtils.getConfigUtils(_this).des_map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[2]);
        }
    }
}
