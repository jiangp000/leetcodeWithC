package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class StageControl {


    private String year;
    private String researchType;
    private String stageType;
    private String submitStageControl;
    private String scoreStageControl;
    private String stageControl;
    private int saveControl;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getSubmitStageControl() {
        return submitStageControl;
    }

    public void setSubmitStageControl(String submitStageControl) {
        this.submitStageControl = submitStageControl;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

    public String getStageControl() {
        return stageControl;
    }

    public void setStageControl(String stageControl) {
        this.stageControl = stageControl;
    }

    public int getSaveControl() {
        return saveControl;
    }

    public void setSaveControl(int saveControl) {
        this.saveControl = saveControl;
    }


    @Override
    public String toString() {
        return "StageControl{" +
                "year='" + year + '\'' +
                ", researchType='" + researchType + '\'' +
                ", stageType='" + stageType + '\'' +
                ", submitStageControl=" + submitStageControl +
                ", scoreStageControl=" + scoreStageControl +
                ", stageControl=" + stageControl +
                ", saveControl=" + saveControl +
                '}';
    }

    public String toCSVStr() {
        return year + "," + researchType + "," + stageType + ","+ submitStageControl + ","
                + scoreStageControl + "," + stageControl + "," + saveControl;
    }
}
