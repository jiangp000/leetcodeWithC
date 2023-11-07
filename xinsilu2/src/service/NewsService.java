package service;

import dao.FilesDAO;
import dao.NewsDAO;
import domain.News;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class NewsService {
    private NewsDAO newsDAO;
    private FilesDAO filesDAO;

    public NewsService() {
        newsDAO = new NewsDAO();
        filesDAO = new FilesDAO();
    }

    public void insertNews(News news) {
        newsDAO.insertNews(news);
    }

    public void updateNews(News news) {
        newsDAO.updateNews(news);
    }

    public void deleteNews(int id) {
        newsDAO.deleteNews(id);
    }

    public ArrayList<News> getNewsListByType(int type, int start) {
        return newsDAO.getNewsListByType(type, start);
    }

    public ArrayList<News> getNewsList(int start) {
        return newsDAO.getNewsList(start);
    }

    public News getNewsById(int id) {
        News news = newsDAO.getNewsById(id);
        news.setFileList(filesDAO.getFileListByNewsId(id));
        return news;
    }


//    public static void main(String[] args){
//        News news = new NewsService().getNewsById(1);
//        ArrayList<Files> fileList = news.getFileList();
//        for(int i=0;i<fileList.size();i++){
//            System.out.println(fileList.get(i).getFileName());
//        }
//    }

}
