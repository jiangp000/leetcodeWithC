package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class TeacherInnoAllScore {


    private String projectNo;
    private String judgeId;
    private String judgeName;
    private String isPass;
    private String projectApplySort;
    private String caseSort;
    private String projectSort;
    private String userId;
    private String username;
    private String department;
    private String title;
    private String teamMember;
    private double judgeScore1;
    private double judgeScore2;
    private double judgeScore3;
    private double judgeScore4;
    private double judgeScore5;
    private double videoScore;
    private double judgeScore6;
    private double judgeScore7;
    private double judgeScore8;
    private double judgeScore9;
    private double documentScore;
    private double preScore;
    private double judgeScore10;
    private double judgeScore11;
    private double judgeScore12;
    private double judgeScore13;
    private double judgeScore14;
    private double pptScore;
    private double score;
    private String applyPreComment;
    private String assessment;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getProjectApplySort() {
        return projectApplySort;
    }

    public void setProjectApplySort(String projectApplySort) {
        this.projectApplySort = projectApplySort;
    }

    public String getCaseSort() {
        return caseSort;
    }

    public void setCaseSort(String caseSort) {
        this.caseSort = caseSort;
    }

    public String getProjectSort() {
        return projectSort;
    }

    public void setProjectSort(String projectSort) {
        this.projectSort = projectSort;
    }

    public String getApplyPreComment() {
        return applyPreComment;
    }

    public void setApplyPreComment(String applyPreComment) {
        this.applyPreComment = applyPreComment;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public double getJudgeScore1() {
        return judgeScore1;
    }

    public void setJudgeScore1(double judgeScore1) {
        this.judgeScore1 = judgeScore1;
    }

    public double getJudgeScore2() {
        return judgeScore2;
    }

    public void setJudgeScore2(double judgeScore2) {
        this.judgeScore2 = judgeScore2;
    }

    public double getJudgeScore3() {
        return judgeScore3;
    }

    public void setJudgeScore3(double judgeScore3) {
        this.judgeScore3 = judgeScore3;
    }

    public double getJudgeScore4() {
        return judgeScore4;
    }

    public void setJudgeScore4(double judgeScore4) {
        this.judgeScore4 = judgeScore4;
    }

    public double getJudgeScore5() {
        return judgeScore5;
    }

    public void setJudgeScore5(double judgeScore5) {
        this.judgeScore5 = judgeScore5;
    }

    public double getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(double videoScore) {
        this.videoScore = videoScore;
    }

    public double getDocumentScore() {
        return documentScore;
    }

    public void setDocumentScore(double documentScore) {
        this.documentScore = documentScore;
    }

    public double getPreScore() {
        return preScore;
    }

    public void setPreScore(double preScore) {
        this.preScore = preScore;
    }

    public double getJudgeScore6() {
        return judgeScore6;
    }

    public void setJudgeScore6(double judgeScore6) {
        this.judgeScore6 = judgeScore6;
    }

    public double getJudgeScore7() {
        return judgeScore7;
    }

    public void setJudgeScore7(double judgeScore7) {
        this.judgeScore7 = judgeScore7;
    }

    public double getJudgeScore8() {
        return judgeScore8;
    }

    public void setJudgeScore8(double judgeScore8) {
        this.judgeScore8 = judgeScore8;
    }

    public double getJudgeScore9() {
        return judgeScore9;
    }

    public void setJudgeScore9(double judgeScore9) {
        this.judgeScore9 = judgeScore9;
    }

    public double getJudgeScore10() {
        return judgeScore10;
    }

    public void setJudgeScore10(double judgeScore10) {
        this.judgeScore10 = judgeScore10;
    }

    public double getJudgeScore11() {
        return judgeScore11;
    }

    public void setJudgeScore11(double judgeScore11) {
        this.judgeScore11 = judgeScore11;
    }

    public double getJudgeScore12() {
        return judgeScore12;
    }

    public void setJudgeScore12(double judgeScore12) {
        this.judgeScore12 = judgeScore12;
    }

    public double getJudgeScore13() {
        return judgeScore13;
    }

    public void setJudgeScore13(double judgeScore13) {
        this.judgeScore13 = judgeScore13;
    }

    public double getJudgeScore14() {
        return judgeScore14;
    }

    public void setJudgeScore14(double judgeScore14) {
        this.judgeScore14 = judgeScore14;
    }

    public double getPptScore() {
        return pptScore;
    }

    public void setPptScore(double pptScore) {
        this.pptScore = pptScore;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String toCSVStr() {
        return userId + "," + isPass + "," + projectApplySort + "," + caseSort + "," +projectSort + ","
                + username + "," + department + "," + title + "," + teamMember+ "," +
                String.valueOf(judgeScore1) + "," + String.valueOf(judgeScore2) + "," +
                String.valueOf(judgeScore3) + "," + String.valueOf(judgeScore4) + "," +
                String.valueOf(judgeScore5) + "," + String.valueOf(videoScore) + "," +
                String.valueOf(judgeScore6) + "," + String.valueOf(judgeScore7) + "," +
                String.valueOf(judgeScore8) + "," + String.valueOf(judgeScore9) + "," +
                String.valueOf(documentScore) + "," + String.valueOf(preScore) + "," +
                String.valueOf(judgeScore10) + "," + String.valueOf(judgeScore11)+ "," +
                String.valueOf(judgeScore12) + "," + String.valueOf(judgeScore13)+ "," +
                String.valueOf(judgeScore14) + "," + String.valueOf(score)+ "," +
                applyPreComment+ "," + assessment;
    }
}
