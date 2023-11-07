package domain;

import util.UploadUtil;

import java.sql.Date;

/**
 * Created by jinggu on 19/4/25.
 */
public class ProjectEnroll {
    private int id;
    private Date submitTime;
    private String userId;
    private String username;
    /*
    所属院系
     */
    private String department;
    /*
    项目类别:  一般  优先  重点
    */
    private String projectType;
    /*
    申请资金 万元
    */
    private float found;
    /*
    完成期限 月份
     */
    private int months;
    /*
    研究课题题目
     */
    private String title;
    private String filePath;
    private String userCV;
    private int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserCV() {
        return userCV;
    }

    public void setUserCV(String userCV) {
        this.userCV = userCV;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public float getFound() {
        return found;
    }

    public void setFound(float found) {
        this.found = found;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String toCSVStr() {
        filePath = UploadUtil.OutputEnrollDIR + "ProjectEnroll" + filePath.substring(filePath.lastIndexOf("/"));
        return id + "," + submitTime + "," + userId + "," + username + "," + department + "," + title + "," + projectType + "," + found + "," + months + String.valueOf(score);
    }

    @Override
    public String toString() {
        return "ProjectEnroll{" +
                "id=" + id +
                ", submitTime=" + submitTime +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                ", userCV='" + userCV + '\'' +
                ", score='" + score +
                '}';
    }
}
