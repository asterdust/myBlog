package dev.mlqs.myblog.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtils {

    public HashMap<String, String[]> des_map = new HashMap<String, String[]>(){{
        put("title", new String[] {"标题", "", ""});
        put("subtitle", new String[] {"副标题", "", ""});
        put("description", new String[] {"网站描述", "", ""});
        put("owner_name", new String[] {"博主姓名", "", ""});
        put("head_img", new String[] {"博主头像", "", ""});
        put("logo", new String[] {"博客logo", "默认/static/img/logo.png", "/static/img/logo.png"});
        put("rating", new String[] {"Gravatar头像分级", "g(全年龄), pg(R12), r(R16), x(R18)，默认pg", "pg"});
        put("img_random_url", new String[] {"随机图片API", "用于登录界面和主界面的图片随机，默认https://picsum.photos/1600/900/?random", ""});
    }};

    private static ConfigUtils configUtils;

    public static ConfigUtils getConfigUtils(Object _this) {
        if (configUtils == null) {
            try {
                configUtils = new ConfigUtils(_this.getClass().getResource("/").getPath() + "blog-info.conf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configUtils;
    }

    private String uri;

    public ConfigUtils(String uri) throws IOException {
        this.uri = uri;
        refresh();
    }

    public void refresh () throws IOException {
        FileInputStream fis = new FileInputStream(uri);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String str;
        String t[];
        for (; (str =  br.readLine()) != null; ) {
            t = str.split("==");
            if (t[0] == null || t[0].equals("") || t[1] == null || t[1].equals("")) continue;
            setValue(t[0], t[1]);
        }
        br.close();
        isr.close();
        fis.close();
    }

    public void save () {
        try {
            FileOutputStream fos = new FileOutputStream(uri);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            for (Map.Entry<String, String[]> entry : des_map.entrySet()) {
                osw.write(entry.getKey());
                osw.write("==");
                osw.write(entry.getValue()[2]);
                osw.write("\n");
            }
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValue(String key, String value) {
        String t2[] = des_map.get(key);
        if (t2 == null) return;
        t2[2] = value;
        des_map.put(key, t2);
    }
}
