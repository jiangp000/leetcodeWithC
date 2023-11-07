package service;

import dao.SelfResearchScoreDAO;
import domain.TeacherInnoAllScore;
import domain.SelfPaperScore;
import domain.SelfResearchScore;

import java.util.ArrayList;

/**
 * Created by widesui on 20/7/12.
 */
public class SelfResearchScoreService {
    private SelfResearchScoreDAO selfResearchScoreDAO;

    public SelfResearchScoreService() {
        selfResearchScoreDAO = new SelfResearchScoreDAO();
    }

    //20211006查出教师界面的个人项目分数
    public ArrayList<ArrayList<SelfResearchScore>> getSelfResearchScoreByIdYear(String userId, int year) {
        return selfResearchScoreDAO.getSelfResScoreByIdYear(userId, year);
    }

    //查询创新大赛阶段教师页面的个人项目分数
    public ArrayList<TeacherInnoAllScore> getSelfInnoScoreByIdYear(String userId, int year, String scorePhase) {
        return selfResearchScoreDAO.getSelfInnoScoreResByIdYear(userId, year, scorePhase);
    }

    //20211006查出教师界面   教研论文  的个人项目分数
    public ArrayList<SelfPaperScore> getSelfPaperScoreByIdYear(String userId, int year, String researchType) {
        return selfResearchScoreDAO.getSelfPapScoreByIdYear(userId, year, researchType);
    }

    public ArrayList<Integer> getYearsFromResearchScore() {
        return selfResearchScoreDAO.getYearsFromResearchScore();
    }
}
