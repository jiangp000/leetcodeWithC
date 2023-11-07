package service;

import dao.ResearchScoreDAO;
import domain.ResearchEnroll;
import domain.ResearchScore;

import java.util.ArrayList;

/**
 * Created by jinggu on 2019/10/30.
 */
public class ResearchScoreService {

    private ResearchScoreDAO researchScoreDAO;

    public ResearchScoreService() {
        researchScoreDAO = new ResearchScoreDAO();
    }

    public void insertResearchScore(ResearchScore researchScore) {
        researchScoreDAO.insertResearchScore(researchScore);
    }

    public void updateResearchAdminScore(ResearchScore researchScore) {
        researchScoreDAO.updateResearchAdminJudgeScore(researchScore);
    }
    public void updateResearchScore(ResearchScore researchScore) {
        researchScoreDAO.updateResearchScore(researchScore);
    }

    public void updateResearchPaperProjectNo(ResearchEnroll researchEnroll) {
        researchScoreDAO.updateResearchPaperProjectNo(researchEnroll);
    }

    public void updateResearchPaperJudgeRes(ResearchEnroll researchEnroll) {
        researchScoreDAO.updateResearchPaperJudgeResult(researchEnroll);
    }

    public void updateResearchFinalProjectNo(ResearchEnroll researchEnroll) {
        researchScoreDAO.updateResearchFinalProjectNo(researchEnroll);
    }

    public void updateResearchFinalJudgeRes(ResearchEnroll researchEnroll) {
        researchScoreDAO.updateResearchFinalJudgeResult(researchEnroll);
    }

    public void deleteResearchScore(int id) {
        researchScoreDAO.deleteResearchScore(id);
    }

    public int isScored(int judgeId, int researchEnrollId) {
        return researchScoreDAO.isScored(judgeId, researchEnrollId);
    }

    public int getAvgScoreListByResearchEnrollId(int researchEnrollId) {
        return researchScoreDAO.getAvgScoreListByResearchEnrollId(researchEnrollId);
    }

    public ArrayList<ResearchScore> getResearchScoreListByJudgeId(int judgeId) {
        return researchScoreDAO.getResearchScoreListByJudgeId(judgeId);
    }

    public ArrayList<ResearchScore> getResearchScoreByJudgeIdAndResearchEnrollId(int judgeId, int researchEnrollId) {
        return researchScoreDAO.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId, researchEnrollId);
    }

    public ArrayList<ResearchScore> getResearchScoreListByResearchEnrollId(int researchEnrollId) {
        return researchScoreDAO.getResearchScoreByResearchEnrollId(researchEnrollId);
    }

    public void updateScoreSaveStage(String year, String researchType, String judgeName) {
        researchScoreDAO.updateScoreSave(year, researchType, judgeName);
    }


}



