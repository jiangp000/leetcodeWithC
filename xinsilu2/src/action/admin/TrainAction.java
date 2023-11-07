package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Train;
import service.TrainService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class TrainAction extends ActionSupport {
    private Train train;
    private int year;
    private int startPage;
    private ArrayList<Train> list;
    private ArrayList<Integer> years;
    /*列出所有可选时间段*/
    private ArrayList<String> quantums;

    private TrainService trainService;

    public TrainAction() {
        trainService = new TrainService();
        startPage = 1;
        quantums = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            quantums.add(String.format("%02d:00", i));
        }
    }

    public String train() {
        years = trainService.getYearsFromTrain();
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        list = trainService.getTrainListByYear(year);
        return SUCCESS;
    }

    public String addTrain() {
        trainService.insertTrain(train);
        return SUCCESS;
    }

    public void validateAddTrain() {
        if (train == null) {
            this.addFieldError("train", "新闻不能为空");
            return;
        }
        if (train.getTitle() == null || train.getTitle().trim().equals("")) {
            this.addFieldError("title", "培训标题不能为空");
        }
        if (train.getContent() == null || train.getContent().trim().equals("")) {
            this.addFieldError("addNewsContent", "培训内容不能为空");
        }
    }

    public String editTrainJSP() {
        if (train == null || train.getId() == 0) {
            return INPUT;
        }
        train = trainService.getTrainById(train.getId());
        if (train == null) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String editTrain() {
        if (train == null) {
            return INPUT;
        }
        trainService.updateTrain(train);
        return SUCCESS;
    }

    public String deleteTrain() {
        if (train.getId() == 0)
            return INPUT;
        trainService.deleteTrain(train.getId());
        return SUCCESS;
    }


    public ArrayList<Train> getList() {
        return list;
    }

    public void setList(ArrayList<Train> list) {
        this.list = list;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public ArrayList<String> getQuantums() {
        return quantums;
    }

    public void setQuantums(ArrayList<String> quantums) {
        this.quantums = quantums;
    }

}
