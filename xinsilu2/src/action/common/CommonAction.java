package action.common;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.News;
import service.FilesService;
import service.NewsService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class CommonAction extends ActionSupport {
    private ArrayList<News> newsList;
    private ArrayList<Files> fileList;
    private News news;
    private int startPage;
    private NewsService newsService;
    private FilesService filesService;

    public CommonAction() {
        newsService = new NewsService();
        filesService = new FilesService();
        startPage = 1;
    }

    public String execute() {
        newsList = newsService.getNewsListByType(0, 1);
        fileList = filesService.getFileListByType(0, 1);
        return SUCCESS;
    }

    public String getAllNews() {
        newsList = newsService.getNewsList(startPage);
        if (newsList.size() == 0) {
            startPage = 1;
            newsList = newsService.getNewsList(startPage);
        }
        return SUCCESS;
    }

    public String newsInfo() {
        if (news == null || news.getId() == 0)
            return INPUT;
        news = newsService.getNewsById(news.getId());
        fileList = filesService.getFileListByNewsId(news.getId());
        return SUCCESS;
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}
