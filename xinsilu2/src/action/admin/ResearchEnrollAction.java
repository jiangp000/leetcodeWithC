package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.ResearchEnroll;
import service.ResearchEnrollService;
import util.CSVUtil;
import util.XLSXUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class ResearchEnrollAction extends ActionSupport {
    private int year;
    private String researchType;
    private ArrayList<Integer> years;
    private ArrayList<Files> fileList;
    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchEnrollService researchEnrollService;

    public ResearchEnrollAction() {
        researchEnrollService = new ResearchEnrollService();
    }

    public String outputResearchJSP() {
        years = researchEnrollService.getYearsFromResearchEnroll();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    public String outputResearch() {
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResearchEnroll/";
        String fileName = String.valueOf(year) + "-" + "课题研究申请汇总表";
        String fields = "序号,申请时间,工号,用户名,申请类型,评分,个人简介,课题申请材料的文件路径";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";

        }
        researchEnrollList = researchEnrollService.getResearchEnrollListByYear(year);
        ArrayList<ResearchEnroll> researchEnrollListCopy = researchEnrollService.getResearchEnrollListByYear(year);

        if (!researchEnrollList.isEmpty()) {
            String link = CSVUtil.createCSVFile(researchEnrollListCopy, fields, filePath, fileName, "domain.ResearchEnroll");
            Files files = new Files();
            files.setFileName(fileName + ".csv");
            files.setFilePath(link);
            fileList.add(files);
        }

        return SUCCESS;
    }

    public String outputResearchApply() {
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResearchEnroll/";
        String fileName = String.valueOf(year) + "-" + researchType + "-" + "课题研究申请汇总表";
        String fields = "序号,申请时间,工号,用户名,申请类型,评分,个人简介,课题申请材料的文件路径";
        String fieldApply = "项目编号,项目分类,是否通过,校园卡号,姓名,院系,项目名称,团队成员1校园卡号,团队成员1姓名,团队成员2校园卡号," +
                "团队成员2姓名,团队成员3校园卡号,团队成员3姓名,团队成员4校园卡号,团队成员4姓名,负责人简介,初审意见,项目阶段";
        String fieldInno = "项目编号,项目类型,案例分组,是否通过,校园卡号,姓名,院系,项目名称,团队成员1校园卡号,团队成员1姓名,团队成员2校园卡号," +
                "团队成员2姓名,团队成员3校园卡号,团队成员3姓名,团队成员4校园卡号,团队成员4姓名,负责人简介,初审意见,项目阶段";
        String fieldPaper = "项目编号,案例分组,填报人卡号,填报人姓名,填报人院系,填报人简介,项目名称,一作卡号,一作姓名,二作卡号," +
                "二作姓名,三作卡号,三作姓名,四作卡号,四作姓名,五作卡号,五作姓名,通讯作者," +
                "负责人卡号,负责人姓名,负责人院系,负责人职称,负责人手机号,负责人邮箱,论文摘要,项目阶段";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";

        }

        //获取每个阶段教师提交的资料的所有信息
        researchEnrollList = researchEnrollService.getResearchApplyInfoByType(researchType, year);
        ArrayList<ResearchEnroll> researchEnrollListCopy = researchEnrollService.getResearchApplyInfoByType(researchType, year);

        if (!researchEnrollList.isEmpty()) {
            try{
                if(researchType.equals("项目申报")){
                    XLSXUtil.createInnoInfoXLSFileWithOneSheet(researchEnrollList, fieldApply, fileName);
                }else if(researchType.equals("创新大赛")){
                    XLSXUtil.createInnoInfoXLSFileWithOneSheet(researchEnrollList, fieldInno, fileName);
                }else if(researchType.equals("中期报告")){
                    XLSXUtil.createInnoInfoXLSFileWithOneSheet(researchEnrollList, fieldInno, fileName);
                }else if(researchType.equals("教研论文")){
                    XLSXUtil.createPaperInfoXLSFileWithOneSheet(researchEnrollList, fieldPaper, fileName);
                }else if(researchType.equals("结题报告")){
                    XLSXUtil.createInnoInfoXLSFileWithOneSheet(researchEnrollList, fieldInno, fileName);
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
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

    public ArrayList<ResearchEnroll> getResearchEnrollList() {
        return researchEnrollList;
    }

    public void setResearchEnrollList(ArrayList<ResearchEnroll> researchEnrollList) {
        this.researchEnrollList = researchEnrollList;
    }
}
