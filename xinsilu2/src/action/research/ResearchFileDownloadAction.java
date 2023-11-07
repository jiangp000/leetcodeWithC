package action.research;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.ResearchEnroll;
import service.ResearchFileDownloadService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static util.UploadUtil.WEBResearchEnrollFilesDIR;

/**
 * Created by widesui on 21/7/12.
 */
public class ResearchFileDownloadAction extends ActionSupport {
    private int year;
    private String userId;
    private String username;
    private String researchType;
    private ArrayList<Integer> years;
    private ArrayList<ResearchEnroll> researchEnrollSelfList;
    private ArrayList<ResearchEnroll> researchEnrollAllList;
    private ResearchFileDownloadService researchFileDownloadService;
    private ArrayList<Files> fileList;

    private ActionContext context;
    private Map<String, Object> session;

    private String file1;
    private String file2;

    public ResearchFileDownloadAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchFileDownloadService = new ResearchFileDownloadService();
        //this.userId = (String)session.get("judge2");
        //this.username = (String)session.get("name");
    }

    public String getFileJSP() {
        years = researchFileDownloadService.getYearsFromResearchScore();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(currentMonth == 11){
            years.add(currentYear+1);
        }
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    public String getResearchFile() {
        /*
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResultEnroll/";
        String fileName = userId + "-" + String.valueOf(year) + "-" + "网评分数表";
        String fields = "项目编号,工号,姓名,课程名,教学理念,教学内容,教学模式,教学效果,视频质量,视频成绩," +
                "教学目标,创新理念思路,创新方法途径,创新效果成果,材料成绩,网评成绩,评语";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }*/
        //String title = researchFileDownloadService.getResearchTitle(userId,username,year);
        //UploadUtil.compressFile(userId,username,year);

        researchEnrollSelfList = researchFileDownloadService.getResearchFileByIdYear(userId,
                    year,researchType);
        if(researchEnrollSelfList.size() >= 1){
            file1 = researchEnrollSelfList.get(0).getOutputZipFilePath();
        }else{
            file1 = "";
        }

        file2 = WEBResearchEnrollFilesDIR + year + "/现场汇报视频/" + researchType + "/"
                    + researchType+"-"+userId+"-"+username+".mp4";




        //researchEnrollAllList = researchFileDownloadService.getResearchFileByYear(year);

        //ArrayList<ResearchEnroll> resultGetListCopy = researchFileDownloadService.getResearchFileByIdYear(userId,username,year);

        /*
        if (!researchEnrollList.isEmpty()) {

            String link = CSVUtil.createCSVFile(resultGetListCopy, fields, filePath, fileName, "domain.ResultGet");
            Files files = new Files();
            files.setFileName(fileName + ".csv");
            files.setFilePath(link);
            fileList.add(files);
        }*/
        return SUCCESS;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUserId(){return userId;}

    public void setUserId(String userId){this.userId = userId;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ArrayList<ResearchEnroll> getResearchEnrollSelfList() {
        return researchEnrollSelfList;
    }

    public void setResearchEnrollSelfList(ArrayList<ResearchEnroll> researchEnrollSelfList) {
        this.researchEnrollSelfList = researchEnrollSelfList;
    }

    public ArrayList<ResearchEnroll> getResearchEnrollAllList() {
        return researchEnrollAllList;
    }

    public void setResearchEnrollAllList(ArrayList<ResearchEnroll> researchEnrollAllList) {
        this.researchEnrollAllList = researchEnrollAllList;
    }

    public ResearchFileDownloadService getResearchFileDownloadService() {
        return researchFileDownloadService;
    }

    public void setResearchFileDownloadService(ResearchFileDownloadService researchFileDownloadService) {
        this.researchFileDownloadService = researchFileDownloadService;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }


}
