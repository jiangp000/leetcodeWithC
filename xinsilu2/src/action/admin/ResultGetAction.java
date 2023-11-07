package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.ResultGet;
import domain.ResultScore;
import service.ResultGetService;
import util.CSVUtil;
import util.XLSXUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by widesui on 21/7/12.
 */
public class ResultGetAction extends ActionSupport {
    private int year;
    private ArrayList<Integer> years;
    private ArrayList<ResultGet> resultGetList;
    private ArrayList<ResultScore> resultScoreList;
    private ResultGetService resultGetService;
    private ArrayList<Files> fileList;

    public ResultGetAction() {
        resultGetService = new ResultGetService();
    }

    public String outputPaperResult(){
        return outputPapResult();
    }


    public String outputResultJSP() {
        years = resultGetService.getYearsFromResearchScore();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }
    private String outputPapResult(){
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResultEnroll/";
        String fileName = String.valueOf(year) + "-" + "网评分数表";
        String fields = "项目编号,工号,姓名,课程名,院系,网审成绩,评分次数";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        resultScoreList = resultGetService.getPaperFinalScoreByYear(year);
        ArrayList<ResultScore> resultGetListCopy = resultGetService.getPaperFinalScoreByYear(year);
        ArrayList<ResultScore> paperScore = new ArrayList<>();
        ArrayList<ResultScore> finalScore = new ArrayList<>();
        for(int i = 0; i < resultGetListCopy.size(); i++){
            if(resultGetListCopy.get(i).getResearchType().equals("教研论文")){
                paperScore.add(resultGetListCopy.get(i));
            }else{
                finalScore.add(resultGetListCopy.get(i));
            }
        }
        ArrayList<ArrayList<ResultScore>> resultScore = new ArrayList<>();
        resultScore.add(paperScore);
        resultScore.add(finalScore);

        if(!paperScore.isEmpty()){
            try{
                XLSXUtil.createPaperFinalXLSFileWithSheets(resultScore, fields, fileName);
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        return SUCCESS;
    }

    public String outputResult() {
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResultEnroll/";
        String fileName = String.valueOf(year) + "-" + "网评分数表";
        String fields = "作品编号,工号,姓名,课程名,案例实录视频得分,申报书等材料得分,网审成绩,评分次数";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        resultGetList = resultGetService.getResearchScoreListByYear(year);
        ArrayList<ResultGet> resultGetListCopy = resultGetService.getResearchScoreListByYear(year);

        if (!resultGetList.isEmpty()) {
            String link = CSVUtil.createCSVFile(resultGetListCopy, fields, filePath, fileName, "domain.ResultGet");
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

    public void setResultGetList(ArrayList<ResultGet> resultGetList) {
        this.resultGetList = resultGetList;
    }

    public void setResultGetService(ResultGetService resultGetService) {
        this.resultGetService = resultGetService;
    }

    public ArrayList<ResultGet> getResultGetList() {
        return resultGetList;
    }

    public ResultGetService getResultGetService() {
        return resultGetService;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
