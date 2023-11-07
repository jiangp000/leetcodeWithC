package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class TeacherInnoResultScore {
    // 所在组
    private String projectNo;
    private String projectApplySort;
    private String caseSort;
    private String projectSort;
    private String userId;
    private String userName;
    private String title;
    private String department;
    private String teamMember;
    private String teamNumber;
    //private String judgeScores;
    private Double judgeScore1;
    private Double judgeScore2;
    private Double judgeScore3;
    private Double judgeScore4;
    private Double judgeScore5;
    private Double judgeScore6;
    private Double judgeScore7;
    private Double judgeScore8;
    private Double judgeScore9;
    private Double judgeScore10;
    private double originalScoreSum;
    private double originalScoreAverage;
    private double weightedScoreSum;
    private double weightedScoreAverage;
    private int ranks;
    private int times;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Double getJudgeScore1() {
        return judgeScore1;
    }

    public void setJudgeScore1(Double judgeScore1) {
        this.judgeScore1 = judgeScore1;
    }

    public Double getJudgeScore2() {
        return judgeScore2;
    }

    public void setJudgeScore2(Double judgeScore2) {
        this.judgeScore2 = judgeScore2;
    }

    public Double getJudgeScore3() {
        return judgeScore3;
    }

    public void setJudgeScore3(Double judgeScore3) {
        this.judgeScore3 = judgeScore3;
    }

    public Double getJudgeScore4() {
        return judgeScore4;
    }

    public void setJudgeScore4(Double judgeScore4) {
        this.judgeScore4 = judgeScore4;
    }

    public Double getJudgeScore5() {
        return judgeScore5;
    }

    public void setJudgeScore5(Double judgeScore5) {
        this.judgeScore5 = judgeScore5;
    }

    public Double getJudgeScore6() {
        return judgeScore6;
    }

    public void setJudgeScore6(Double judgeScore6) {
        this.judgeScore6 = judgeScore6;
    }

    public Double getJudgeScore7() {
        return judgeScore7;
    }

    public void setJudgeScore7(Double judgeScore7) {
        this.judgeScore7 = judgeScore7;
    }

    public Double getJudgeScore8() {
        return judgeScore8;
    }

    public void setJudgeScore8(Double judgeScore8) {
        this.judgeScore8 = judgeScore8;
    }

    public Double getJudgeScore9() {
        return judgeScore9;
    }

    public void setJudgeScore9(Double judgeScore9) {
        this.judgeScore9 = judgeScore9;
    }

    public Double getJudgeScore10() {
        return judgeScore10;
    }

    public void setJudgeScore10(Double judgeScore10) {
        this.judgeScore10 = judgeScore10;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getOriginalScoreSum() {
        return originalScoreSum;
    }

    public void setOriginalScoreSum(double originalScoreSum) {
        this.originalScoreSum = originalScoreSum;
    }

    public double getOriginalScoreAverage() {
        return originalScoreAverage;
    }

    public void setOriginalScoreAverage(double originalScoreAverage) {
        this.originalScoreAverage = originalScoreAverage;
    }

    public double getWeightedScoreSum() {
        return weightedScoreSum;
    }

    public void setWeightedScoreSum(double weightedScoreSum) {
        this.weightedScoreSum = weightedScoreSum;
    }

    public double getWeightedScoreAverage() {
        return weightedScoreAverage;
    }

    public void setWeightedScoreAverage(double weightedScoreAverage) {
        this.weightedScoreAverage = weightedScoreAverage;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }


    @Override
    public String toString() {
        return "DownloadAllScore{" +
                "projectNo='" + projectNo + '\'' +
                ", projectApplySort='" + projectApplySort + '\'' +
                ", caseSort='" + projectSort + '\'' +
                ", projectSort='" + projectSort + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", teamMember='" + teamMember + '\'' +
                ", teamNumber='" + teamNumber + '\'' +
                ", judgeScore1='" + judgeScore1 + '\'' +
                ", originalScoreSum=" + originalScoreSum +
                ", originalScoreAverage=" + originalScoreAverage +
                ", weightedScoreSum=" + weightedScoreSum +
                ", weightedScoreAverage=" + weightedScoreAverage +
                ", ranks=" + ranks +
                ", times='" + times + '\'' +
                '}';
    }

    public String toCSVStr() {
        return projectNo + ","+
                userId + "," + projectApplySort + "," + caseSort + "," + projectSort + "," + userName + "," + title + "," +
                department + "," + teamMember+ "," + teamNumber + "," + judgeScore1 + "," +
                String.valueOf(originalScoreSum) + "," +
                String.valueOf(originalScoreAverage) + "," + String.valueOf(weightedScoreSum) + "," +
                String.valueOf(weightedScoreAverage) + "," + ranks + "," +
                times;
    }
}
