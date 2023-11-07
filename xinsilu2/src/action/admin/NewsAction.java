package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import domain.News;
import service.FilesService;
import service.NewsService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class NewsAction extends ActionSupport {
    private News news;
    private ArrayList<News> list;
    private ArrayList<Files> fileList;
    /*上传多个附件*/
//    private List<File> upload;
//    private List<String> uploadFileName;;
    private int startPage;

    private NewsService newsService;
    private FilesService filesService;

    public NewsAction() {
        newsService = new NewsService();
        filesService = new FilesService();
        startPage = 1;
    }

    public String news() {
        list = newsService.getNewsList(startPage);
        return SUCCESS;
    }

    //TODO
    /*新建新闻时,发表时间有问题*/
    public String addNews() {
        newsService.insertNews(news);
        return SUCCESS;
    }

    public void validateAddNews() {
        if (news == null) {
            this.addFieldError("addNews", "新闻不能为空");
            return;
        }
        if (news.getTitle() == null || news.getTitle().trim().equals("")) {
            this.addFieldError("addNewsTitle", "新闻标题不能为空");
        }
//        if (news.getContent() == null || news.getContent().trim().equals("")) {
//            this.addFieldError("addNewsContent", "新闻内容不能为空");
//        }
    }

    public String editNewsJSP() {
        if (news == null || news.getId() == 0)
            return INPUT;
        news = newsService.getNewsById(news.getId());
        fileList = filesService.getFileListByNewsId(news.getId());
        return SUCCESS;
    }

    /*TODO:editNews页面需要加个判断,如果news的链接不为空,则可以修改新闻链接*/


    public String editNews() {
        if (news == null)
            return INPUT;
//        System.out.print(news.getPublishTime());
        newsService.updateNews(news);
        return SUCCESS;
    }

    public String deleteNews() {
        if (news.getId() == 0) {
            return INPUT;
        }
        newsService.deleteNews(news.getId());
        return SUCCESS;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public ArrayList<News> getList() {
        return list;
    }

    public void setList(ArrayList<News> list) {
        this.list = list;
    }


    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
