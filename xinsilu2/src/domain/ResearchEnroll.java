package domain;

import util.UploadUtil;

import java.sql.Date;

/**
 * Created by jinggu on 19/4/28.
 */
public class ResearchEnroll {
    private int id;
    private Date submitTime;
    private String userId;
    private String username;
    private String projectNo;
    private String title;
    private String filePath;
    private String userCV;
    private String researchType;
    private int score;
    private String outputZipFilePath;
    private String deptName;
    private String projectSort;
    private String paperAbstract;
    private String caseSort;
    private String paperUserid;
    private String paperUsername;
    private String paperUserdept;
    private String userTitle;
    private String userPhone;
    private String userMail;
    private String teamMember;
    private String projectEthicSort;
    private String projectEthicPath;
    private String projectApplySort;
    private String isPass;
    private String applyPreComment;
    private String applyId;
    private String applyTime;
    private String submitSave;
    private String delaySort;
    private String projectTitle;



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

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public String getOutputZipFilePath() {
        return outputZipFilePath;
    }

    public void setOutputZipFilePath(String outputZipFilePath) {
        this.outputZipFilePath = outputZipFilePath;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProjectSort() {
        return projectSort;
    }

    public void setProjectSort(String projectSort) {
        this.projectSort = projectSort;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getCaseSort() {
        return caseSort;
    }

    public void setCaseSort(String caseSort) {
        this.caseSort = caseSort;
    }

    public String getPaperUserid() {
        return paperUserid;
    }

    public void setPaperUserid(String paperUserid) {
        this.paperUserid = paperUserid;
    }

    public String getPaperUsername() {
        return paperUsername;
    }

    public void setPaperUsername(String paperUsername) {
        this.paperUsername = paperUsername;
    }

    public String getPaperUserdept() {
        return paperUserdept;
    }

    public void setPaperUserdept(String paperUserdept) {
        this.paperUserdept = paperUserdept;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getProjectEthicSort() {
        return projectEthicSort;
    }

    public void setProjectEthicSort(String projectEthicSort) {
        this.projectEthicSort = projectEthicSort;
    }

    public String getProjectEthicPath() {
        return projectEthicPath;
    }

    public void setProjectEthicPath(String projectEthicPath) {
        this.projectEthicPath = projectEthicPath;
    }

    public String getProjectApplySort() {
        return projectApplySort;
    }

    public void setProjectApplySort(String projectApplySort) {
        this.projectApplySort = projectApplySort;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getApplyPreComment() {
        return applyPreComment;
    }

    public void setApplyPreComment(String applyPreComment) {
        this.applyPreComment = applyPreComment;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getSubmitSave() {
        return submitSave;
    }

    public void setSubmitSave(String submitSave) {
        this.submitSave = submitSave;
    }

    public String getDelaySort() {
        return delaySort;
    }

    public void setDelaySort(String delaySort) {
        this.delaySort = delaySort;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String toCSVStr() {
        filePath = UploadUtil.OutputEnrollDIR + "ResearchEnroll" + filePath.substring(filePath.lastIndexOf("/"));
        return id + "," + submitTime + "," + userId + "," + username+ "," + projectNo + "," +
                title + "," + filePath + "," + userCV + "," + researchType + "," + String.valueOf(score) + "," +
                outputZipFilePath + "," + deptName + "," + projectSort + "," + paperAbstract + "," +
                paperUserid + "," + paperUsername + "," + paperUserdept + "," +
                userTitle + "," + userPhone + "," + userMail + "," + teamMember + ","  + projectEthicSort + "," +
                projectEthicPath + "," + projectApplySort + "," + isPass + "," + applyPreComment + "," + applyId + "," +
                applyTime + "," +submitSave;
    }
}
