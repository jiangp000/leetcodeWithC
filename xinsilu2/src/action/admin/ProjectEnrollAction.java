package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.ProjectEnroll;
import service.ProjectEnrollService;
import util.CSVUtil;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class ProjectEnrollAction extends ActionSupport {
    private int year;
    private ArrayList<Integer> years;
    private ArrayList<Files> fileList;

    private ProjectEnrollService projectEnrollService;

    public ProjectEnrollAction() {
        projectEnrollService = new ProjectEnrollService();
    }

    public String outputProjectJSP() {
        years = projectEnrollService.getYearsFromProjectEnroll();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    public String outputProject() {
        String filePath = "ProjectEnroll/";
        String fileName = String.valueOf(year) + "-" + "项目申请汇总表";
        String fields = "序号,申请时间,工号,负责人姓名,所属院系,课题题目,项目类别,申请资金,完成期限,最终得分";
        fileList = new ArrayList<>();
        if (year == 0)
            return "noyear";
        ArrayList<ProjectEnroll> projectEnrollList = projectEnrollService.getEnrollListByYear(year);
        if (!projectEnrollList.isEmpty()) {
            String link = CSVUtil.createCSVFile(projectEnrollList, fields, filePath, fileName, "domain.ProjectEnroll");
            Files files = new Files();
            files.setFileName(fileName + ".csv");
            files.setFilePath(link);
            fileList.add(files);
        }
        return SUCCESS;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public void setYears(ArrayList<Integer> years) {
        this.years = years;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
