package service;

import dao.DownloadScoreDAO;
import domain.DownloadInnoAllScore;
import domain.DownloadScore;

import java.util.ArrayList;

/**
 * Created by widesui on 20/7/13.
 */
public class DownloadScoreService {
    private DownloadScoreDAO downloadScoreDAO;

    public DownloadScoreService() {

        downloadScoreDAO = new DownloadScoreDAO();
    }


    public ArrayList<DownloadScore> getResearchScoreListByYear(int year, String userId) {
        return downloadScoreDAO.getScoreByYear(year, userId);
    }

    public ArrayList<DownloadInnoAllScore> getResearchScoreListByYearType(int year, String userId, String researchType) {
        return downloadScoreDAO.getScoreByYearType(year, userId, researchType);
    }

    public ArrayList<DownloadInnoAllScore> getInnoScoreListByYearType(int year, String userId, String researchType, String scorePhase) {
        return downloadScoreDAO.getInnoScoreByYearType(year, userId, researchType, scorePhase);
    }


    public ArrayList<Integer> getYearsFromResearchScore(String userId) {
        return downloadScoreDAO.getYearsFromResearchScore(userId);
    }


}
