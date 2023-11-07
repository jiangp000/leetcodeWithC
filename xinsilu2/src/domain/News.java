package domain;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/28.
 */
public class News {
    private int id;
    private Date publishTime;
    private String title;
    private String content;
    private String link;
    /*:type: 0属于培训类，1属于交流研讨类*/
    private int type = 0;
    private ArrayList<Files> fileList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
