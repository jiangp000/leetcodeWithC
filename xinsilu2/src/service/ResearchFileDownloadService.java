package service;

import dao.ResearchFileDownloadDAO;
import dao.SelfResearchScoreDAO;
import domain.ResearchEnroll;
import domain.SelfResearchScore;

import java.util.ArrayList;

/**
 * Created by widesui on 20/7/12.
 */
public class ResearchFileDownloadService {
    private ResearchFileDownloadDAO researchFileDownloadDAO;

    public ResearchFileDownloadService() {
        researchFileDownloadDAO = new ResearchFileDownloadDAO();
    }

    //20211006查出教师界面的个人项目文件
    public ArrayList<ResearchEnroll> getResearchFileByIdYear(String userId, int year,String researchType) {
        return researchFileDownloadDAO.getResFileByIdYear(userId, year,researchType);
    }

    //20211006查出教师界面的全部项目文件
    public ArrayList<ResearchEnroll> getResearchFileByYear(int year) {
        return researchFileDownloadDAO.getResFileByYear(year);
    }

    public ArrayList<Integer> getYearsFromResearchScore() {
        return researchFileDownloadDAO.getYearsFromResearchScore();
    }
}
