package service;

import dao.ProjectEnrollDAO;
import domain.ProjectEnroll;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class ProjectEnrollService {
    private ProjectEnrollDAO projectEnrollDAO;

    public ProjectEnrollService() {
        projectEnrollDAO = new ProjectEnrollDAO();
    }

    public void insertProjectEnroll(ProjectEnroll projectEnroll) {
        projectEnrollDAO.insertProjectEnroll(projectEnroll);
    }

    public void updateProjectEnrollScore(int score, int id) {
        projectEnrollDAO.updateProjectEnrollScore(score, id);
    }

    public ProjectEnroll getProjectEnrollById(int id) {
        return projectEnrollDAO.getProjectEnrollById(id);
    }

    /*评审时展示仅两个月的申请*/
    public ArrayList<ProjectEnroll> getEnrollListRecent() {
        return projectEnrollDAO.getEnrollListRecent();
    }

    public ProjectEnroll getEnrollByUserIdAndDate(String userId, Date date) {
        return projectEnrollDAO.getEnrollByUserIdAndDate(userId, date);
    }
    /*管理员按年查询申请情况*/
    public ArrayList<ProjectEnroll> getEnrollListByYear(int year) {
        return projectEnrollDAO.getEnrollListByYear(year);
    }

    public ArrayList<Integer> getYearsFromProjectEnroll() {
        return projectEnrollDAO.getYearsFromProjectEnroll();
    }


}
