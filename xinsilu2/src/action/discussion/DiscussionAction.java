package action.discussion;

import com.opensymphony.xwork2.ActionSupport;
import domain.News;
import service.NewsService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class DiscussionAction extends ActionSupport {
    private ArrayList<News> newsList;
    private int startPage;
    private NewsService newsService;

    public DiscussionAction() {
        newsService = new NewsService();
        startPage = 1;
    }

    public String execute() {
        newsList = newsService.getNewsListByType(1, startPage);
        if (newsList.size() == 0) {
            startPage = 1;
            newsList = newsService.getNewsListByType(1, startPage);
        }
        return SUCCESS;
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
