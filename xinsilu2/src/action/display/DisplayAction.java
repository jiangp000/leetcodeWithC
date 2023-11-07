package action.display;

import com.opensymphony.xwork2.ActionSupport;
import domain.Teacher;
import service.TeacherService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */

public class DisplayAction extends ActionSupport {
    private ArrayList<Integer> years;
    private ArrayList<Teacher> teachers;
    private int year;
    private Teacher teacher;
    private TeacherService teacherService;

    public DisplayAction() {
        teacherService = new TeacherService();
    }

    //TODO
    /*按照教师姓名搜索*/


    public String execute() {
        years = teacherService.getYearsFromResearchEnroll();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(0);
        }
        teachers = teacherService.getTeacherListFromResearchEnrollByYear(year);
        return SUCCESS;
    }

    public String teacher() {
        if (teacher.getUserId().trim().equals("") || year == 0) {
            return INPUT;
        }
        teacher = teacherService.getTeacherByUserId(teacher.getUserId(), year);
        return SUCCESS;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public void setYears(ArrayList<Integer> years) {
        this.years = years;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
