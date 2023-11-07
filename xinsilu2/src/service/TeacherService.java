package service;

import dao.TeacherDAO;
import domain.Teacher;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class TeacherService {
    private TeacherDAO teacherDAO;

    public TeacherService() {
        teacherDAO = new TeacherDAO();
    }

    public ArrayList<Integer> getYearsFromResearchEnroll() {
        return teacherDAO.getYearsFromResearchEnroll();

    }



    public ArrayList<Teacher> getTeacherListFromResearchEnrollByYear(int year) {
        return teacherDAO.getTeacherListFromResearchEnrollByYear(year);
    }

    public Teacher getTeacherByUserId(String id, int year) {
        return teacherDAO.getTeacherByUserId(id, year);
    }

    public Teacher getTeacherByUserIdAndDate(String id, Date date) {
        return teacherDAO.getTeacherByUserIdAndDate(id, date);
    }

}
