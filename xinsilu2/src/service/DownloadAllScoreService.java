package service;

import dao.DownloadAllScoreDAO;
import domain.*;

import java.util.ArrayList;

/**
 * Created by widesui on 20/7/13.
 */
public class DownloadAllScoreService {
    private DownloadAllScoreDAO downloadAllScoreDAO;

    public DownloadAllScoreService() {

        downloadAllScoreDAO = new DownloadAllScoreDAO();
    }


    public ArrayList<ArrayList<DownloadAllScore>> getResearchScoreListByYear(int year) {
        return downloadAllScoreDAO.getScoreByYear(year);
    }

    public ArrayList<ArrayList<DownloadApplyAllScore>> getApplyJudgeScoreList(int year, String researchType, String isPre) {
        return downloadAllScoreDAO.getApplyJudgeScores(year, researchType, isPre);
    }
    public ArrayList<DownloadApplyResultScore> getApplyResultScoreList(int year, String researchType, String isPre) {
        return downloadAllScoreDAO.getApplyResultScores(year, researchType, isPre);
    }

    public ArrayList<ArrayList<DownloadInnoAllScore>> getInnoPreJudgeScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getInnoPreJudgeScores(year, researchType);
    }

    public ArrayList<ArrayList<DownloadInnoAllScore>> getInnoJudgeScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getInnoJudgeScores(year, researchType);
    }


    public ArrayList<DownloadInnoResultScore> getInnoPreScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getInnoPreScore(year, researchType);
    }

    public ArrayList<DownloadInnoResultScore> getInnoResultScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getInnoResultScore(year, researchType);
    }

    public ArrayList<ArrayList<DownloadPaperAllScore>> getPaperJudgeScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getPaperJudgeScores(year, researchType);
    }

    public ArrayList<DownloadPaperResultScore> getPaperResultScoreList(int year, String researchType) {
        return downloadAllScoreDAO.getPaperResultScore(year, researchType);
    }
    public ArrayList<ArrayList<DownloadFinalAllScore>> getFinalJudgeScoreList(int year, String researchType, String isPre) {
        return downloadAllScoreDAO.getFinalJudgeScores(year, researchType, isPre);
    }

    public ArrayList<DownloadFinalResultScore> getFinalResultScoreList(int year, String researchType, String isPre) {
        return downloadAllScoreDAO.getFinalResultScore(year, researchType, isPre);
    }


    public ArrayList<Integer> getYearsFromResearchScore() {
        return downloadAllScoreDAO.getYearsFromResearchScore();
    }
}
