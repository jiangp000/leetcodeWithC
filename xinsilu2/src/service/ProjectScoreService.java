package service;

import dao.ProjectScoreDAO;
import domain.ProjectScore;

import java.util.ArrayList;

/**
 * Created by jinggu on 2019/10/30.
 */
public class ProjectScoreService {

    private ProjectScoreDAO projectScoreDAO;

    public ProjectScoreService() {
        projectScoreDAO = new ProjectScoreDAO();
    }

    public void insertProjectScore(ProjectScore projectScore) {
        projectScoreDAO.insertProjectScore(projectScore);
    }

    public void updateProjectScore(ProjectScore projectScore) {
        projectScoreDAO.updateProjectScore(projectScore);
    }

    public void deleteProjectScore(int id) {
        projectScoreDAO.deleteProjectScore(id);
    }

    public int isScored(int judgeId, int projectEnrollId) {
        return projectScoreDAO.isScored(judgeId, projectEnrollId);
    }

    public int getAvgScoreListByProjectEnrollId(int projectEnrollId) {
        return projectScoreDAO.getAvgScoreListByProjectEnrollId(projectEnrollId);
    }

    public ArrayList<ProjectScore> getProjectScoreListByJudgeId(int judgeId) {
        return projectScoreDAO.getProjectScoreListByJudgeId(judgeId);
    }


}



