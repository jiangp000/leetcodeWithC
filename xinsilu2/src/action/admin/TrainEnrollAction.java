package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.Train;
import domain.TrainEnroll;
import service.TrainEnrollService;
import service.TrainService;
import util.CSVUtil;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/11.
 */
public class TrainEnrollAction extends ActionSupport {
    private int year;
    private String trainIds;

    private ArrayList<Integer> years;
    private ArrayList<Train> list;
    private ArrayList<Files> fileList;

    private TrainService trainService;
    private TrainEnrollService trainEnrollService;

    public TrainEnrollAction() {
        trainService = new TrainService();
        trainEnrollService = new TrainEnrollService();
    }

    public String outputTrainJSP() {
        years = trainService.getYearsFromTrain();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        list = trainService.getTrainListByYear(year);
        return SUCCESS;
    }

    public String outputTrain() {
        if (trainIds == null || trainIds.length() == 0) {
            return ERROR;
        }
        String[] ids = trainIds.split(",");
        ArrayList<TrainEnroll> trainEnrollList;
        String fields = "工号,用户名,手机,邮箱";
        String filePath = "TrainEnroll/";
        fileList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            trainEnrollList = trainEnrollService.getTrainEnrollListByTrainId(Integer.valueOf(ids[i]));
            Train train = trainService.getTrainById(Integer.valueOf(ids[i].trim()));
            String fileName = train.getTrainTime() + "-" + train.getTitle();
            String link = CSVUtil.createCSVFile(trainEnrollList, fields, filePath, fileName, "domain.TrainEnroll");
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

    public String getTrainIds() {
        return trainIds;
    }

    public void setTrainIds(String trainIds) {
        this.trainIds = trainIds;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public void setYears(ArrayList<Integer> years) {
        this.years = years;
    }

    public ArrayList<Train> getList() {
        return list;
    }

    public void setList(ArrayList<Train> list) {
        this.list = list;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
