package action.train;

import com.opensymphony.xwork2.ActionSupport;
import domain.News;
import domain.Train;
import service.NewsService;
import service.TrainService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class TrainAction extends ActionSupport {
    public ArrayList<Train> list;
    public ArrayList<News> newsList;
    public int startPage;

    private TrainService trainService;
    private NewsService newsService;

    public TrainAction() {
        trainService = new TrainService();
        newsService = new NewsService();
        startPage = 1;
    }

    public String schedule() {
        list = trainService.getValidTrainList();
        return SUCCESS;
    }

    public String getOldTraining() {
        newsList = newsService.getNewsListByType(0, startPage);
        if (newsList.size() == 0) {
            startPage = 1;
            newsList = newsService.getNewsListByType(0, startPage);
        }
        return SUCCESS;
    }

    public ArrayList<Train> getList() {
        return list;
    }

    public void setList(ArrayList<Train> list) {
        this.list = list;
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}
