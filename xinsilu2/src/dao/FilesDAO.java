package dao;

import domain.Files;
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
public class FilesDAO {
    /* 所有附件文件的增删改查 */
    private String insertFileSql = "insert into files (file_name,file_path,news_id) values (?,?,?) ";
    private String deleteFileSql = "delete from files where id = ?";
    private String getFileListByTypeSql = "select * from files where type=? limit ?,?";
    private String getFileListByNewsIdSql = "select * from files where news_id=?";
    private String getFileByIdSql = "select * from files where id=?";

    private int num = UploadUtil.NUM;

    public void insertFile(Files file) {
        /* 为保证安全,前端页面上传的附件都是文件,可执行文件通过人工手动登录后台上传*/
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertFileSql);
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setString(2, file.getFilePath());
            preparedStatement.setInt(3, file.getNewsId());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteFile(int id) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteFileSql);
            preparedStatement.setInt(1, id);
            // 获取preparedStatement 最终执行的sql语句
            //System.out.print(preparedStatement.toString());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Files getFileById(int id) {
        Files file = null;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(getFileByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                file = getFileFromResultSet(resultSet);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return file;
    }


    public ArrayList<Files> getFileListByType(int type, int start) {
        ArrayList<Files> fileList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getFileListByTypeSql);
            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, (start - 1) * num);
            preparedStatement.setInt(3, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Files file = getFileFromResultSet(resultSet);
                fileList.add(file);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public ArrayList<Files> getFileListByNewsId(int id) {
        ArrayList<Files> fileList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getFileListByNewsIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Files file = getFileFromResultSet(resultSet);
                fileList.add(file);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    private Files getFileFromResultSet(ResultSet resultSet) throws SQLException {
        Files file = new Files();
        file.setId(resultSet.getInt(1));
        file.setFileName(resultSet.getString(2));
        file.setFilePath(resultSet.getString(3));
        file.setNewsId(resultSet.getInt(4));
        return file;
    }

    /*For test*/
    public static void main(String args[]) {
        FilesDAO dao = new FilesDAO();

//        Files file = new Files();
//        file.setFileName("测试附件1");
//        file.setFilePath("/upload/xinsilu/files/testfile1.pdf");
//
//        dao.insertFile(file);

        ArrayList<Files> list = dao.getFileListByType(0, 1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFileName());
        }

//        dao.deleteFile(5);

    }

}
