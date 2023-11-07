package service;

import dao.ResultGetDAO;
import domain.ResultGet;
import domain.ResultScore;

import java.util.ArrayList;

/**
 * Created by widesui on 20/7/12.
 */
public class ResultGetService {
    private ResultGetDAO resultGetDAO;

    public ResultGetService() {
        resultGetDAO = new ResultGetDAO();
    }


    public ArrayList<ResultGet> getResearchScoreListByYear(int year) {
        return resultGetDAO.getResultByYear(year);
    }

    public ArrayList<ResultScore> getPaperFinalScoreByYear(int year) {
        return resultGetDAO.getPaperFinalScoreByYear(year);
    }

    public ArrayList<Integer> getYearsFromResearchScore() {
        return resultGetDAO.getYearsFromResearchScore();
    }
}
