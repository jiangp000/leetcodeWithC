package util;

import java.util.HashMap;

public class ConfUtil {
    // 本地开发配置
    private static conf developConf = new conf(
            "jdbc:mysql://localhost:3306/xinsilu?useUnicode=true&characterEncoding=utf-8&useSSL=false",
            "root",
            "zxczxc123",
            "D:/uploadFiles/",  // 文件找不到，Windows浏览器找文件
            "D:/uploadFiles/",
            "http://localhost:8080/file/");

    // 线上配置
    private static conf productConf =  new conf(
            "jdbc:mysql://162.105.14.84:3310/xinsilu?useUnicode=true&characterEncoding=utf-8&useSSL=false",
            "root",
            "Wxq415@gj409",
            "/upload/xinsilu/",     // 提交文件的位置 ？
            "/jxw/webUpload/xinsilu/",    // 根目录
            "/upload/xinsilu/");
    private static HashMap<String,conf> confs = new HashMap<String, conf>(){{
        put("dev",developConf);
        put("prod",productConf);
    }};

    // todo
//    JiaoShiJiaoXue@409
    // 改变环境（本地开发->上线）时，只需要切换下面两行
//    private static String Env="dev";
    private static String Env="prod";

    public static String getDBURL(){
        return confs.get(Env).getDBURL();
    }
    public static String getDBUsername(){
        return confs.get(Env).getDBUsername();
    }
    public static String getDBPassword(){
        return confs.get(Env).getDBPassword();
    }

    public static String getWebDir(){
        return confs.get(Env).getWebDir();
    }

    public static String getDiskDir(){
        return confs.get(Env).getDiskDir();
    }
    public static String getFileVirtualPath(){return confs.get(Env).getFileVirtualPath();}
}
class conf{
    public String getDBURL() {
        return DBURL;
    }

    public String getDBUsername() {
        return DBUsername;
    }

    public String getDBPassword() {
        return DBPassword;
    }

    public String getDiskDir() {
        return DiskDir;
    }

    public String getWebDir() {
        return WebDir;
    }
    public String getFileVirtualPath(){return FileVirtualPath;};

    private String DBURL;
    private String DBUsername;
    private String DBPassword;
    private String DiskDir;
    private String WebDir;
    private String FileVirtualPath;
    public conf(String url,String username, String password, String webDir, String diskDir, String fileVirtualPath){
        DBURL = url;
        DBUsername = username;
        DBPassword = password;
        WebDir = webDir;
        DiskDir = diskDir;
        FileVirtualPath = fileVirtualPath;
    }
}

