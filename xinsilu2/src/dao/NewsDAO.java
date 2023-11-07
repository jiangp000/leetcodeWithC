package dao;

import domain.News;
import util.DBUtil;
import util.UploadUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/28.
 */
public class NewsDAO {
    private String insertNewsSql = "insert into news (title,content,link,type) values (?,?,?,?);";
    private String updateNewsSql = "update news set title = ?, content=?, link=?, type=?, publish_time=? where id = ?";
    private String deleteNewsSql = "delete from news where id = ?";
    private String getNewsListByTypeSql = "select id,title,link from news where type = ? order by publish_time DESC limit ?, ?";
    private String getNewsByIdSql = "select * from news where id = ?";
    private String getNewsListSql = "select id,title,link from news order by publish_time DESC limit ?,?";
    private int num = UploadUtil.NUM;

    public void insertNews(News news) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertNewsSql);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getLink());
            preparedStatement.setInt(4, news.getType());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNews(News news) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNewsSql);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getLink());
            preparedStatement.setInt(4, news.getType());
            preparedStatement.setDate(5, news.getPublishTime());
            preparedStatement.setInt(6, news.getId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteNews(int id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteNewsSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<News> getNewsListByType(int type, int start) {
        ArrayList<News> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNewsListByTypeSql);
            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, (start - 1) * num);
            preparedStatement.setInt(3, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setLink(resultSet.getString(3));
                list.add(news);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public News getNewsById(int id) {
        News news = new News();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNewsByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                news = getNewsFromResultSet(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public ArrayList<News> getNewsList(int start) {
        ArrayList<News> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getNewsListSql);
            preparedStatement.setInt(1, (start - 1) * num);
            preparedStatement.setInt(2, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setLink(resultSet.getString(3));
                list.add(news);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private News getNewsFromResultSet(ResultSet resultSet) throws SQLException {
        News news = new News();
        news.setId(resultSet.getInt(1));
        news.setPublishTime(resultSet.getDate(2));
        news.setTitle(resultSet.getString(3));
        news.setContent(resultSet.getString(4));
        news.setLink(resultSet.getString(5));
        news.setType(resultSet.getInt(6));
        return news;
    }

    public static void main(String args[]) {

        NewsDAO dao = new NewsDAO();

//        News news = new News();
//        news.setId(6);
//        news.setTitle("测试新闻2");
//        news.setContent("测试新闻内容1");
//        news.setFilePaths("/upload/xinsilu/news/testnews1.pdf");
//        news.setLink("https://test");

//        dao.insertNews(news);
//        dao.updateNews(news);
//        dao.deleteNews(6);

        ArrayList<News> list = dao.getNewsListByType(0, 1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }


    }


}
