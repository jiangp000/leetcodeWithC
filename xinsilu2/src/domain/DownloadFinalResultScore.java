package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class DownloadFinalResultScore {
    // 所在组
    private String projectNo;
    private String userId;
    private String userName;
    private String deptName;
    private String title;
    private double judgeScore1;
    private double judgeScore2;
    private double judgeScore3;
    private double judgeScore4;
    private double judgeScore5;
    private double judgeScore6;
    private double judgeScore7;
    private double judgeScore8;
    private double judgeScore9;
    private double judgeScore10;
    private double judgeScore11;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", title='" + title + '\'' +
                ", judgeScore1=" + judgeScore1 +
                ", judgeScore2=" + judgeScore2 +
                ", judgeScore3=" + judgeScore3 +
                ", judgeScore4=" + judgeScore4 +
                ", judgeScore5=" + judgeScore5 +
                ", judgeScore6=" + judgeScore6 +
                ", judgeScore7=" + judgeScore7 +
                ", judgeScore8=" + judgeScore8 +
                ", judgeScore9=" + judgeScore9 +
                ", judgeScore10=" + judgeScore10 +
                ", judgeScore11=" + judgeScore11 +
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
                userId + "," + userName + "," + deptName + "," + title + "," + String.valueOf(judgeScore1) + "," +
                String.valueOf(judgeScore2) + "," + String.valueOf(judgeScore3) + "," +
                String.valueOf(judgeScore4) + "," + String.valueOf(judgeScore5) + "," +
                String.valueOf(judgeScore6) + "," + String.valueOf(judgeScore7) + "," +
                String.valueOf(judgeScore8) + "," + String.valueOf(judgeScore9) + "," +
                String.valueOf(judgeScore10) + "," + String.valueOf(judgeScore11) + "," +
                String.valueOf(originalScoreSum) + "," +
                String.valueOf(originalScoreAverage) + "," + String.valueOf(weightedScoreSum) + "," +
                String.valueOf(weightedScoreAverage) + "," + ranks + "," +
                times;
    }
}
