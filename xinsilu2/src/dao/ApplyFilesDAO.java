package dao;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplyFilesDAO {
    private String getFilePathsSql = "select id,file_root_path,files_name from apply_files where user_id=? and research_type=? and year=?";
    private String insertFilePathsSql = "insert into apply_files (user_id,research_type,year,file_root_path,files_name) values (?,?,?,?,?)";
    private String updateFilePathsSql = "update apply_files set files_name=? where id=?";
    private String deleteFilePathsSql = "delete from apply_files where user_id=? and research_type=? and year=?";
    public void insertFilePaths(String userID, String fileType, int year, String fileRootPath, String[] fileNameList){
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertFilePathsSql);
            preparedStatement.setString(1,userID);
            preparedStatement.setString(2,fileType);
            preparedStatement.setInt(3,year);
            preparedStatement.setString(4,fileRootPath);
            String filesName = "";
            for(int i = 0; i < fileNameList.length-1; i++){
                filesName+=fileNameList[i]+",";
            }
            filesName+=fileNameList[fileNameList.length-1];
            preparedStatement.setString(5,filesName);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateFilePaths(String id, String[] fileNameList){
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateFilePathsSql);
            String filesName = "";
            for(int i = 0; i < fileNameList.length-1; i++){
                filesName+=fileNameList[i]+",";
            }
            filesName+=fileNameList[fileNameList.length-1];
            preparedStatement.setString(1,filesName);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getFilePaths(String userID, String fileType, int year){
        Connection connection = DBUtil.getConnection();
        ArrayList<String> result = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getFilePathsSql);
            preparedStatement.setString(1,userID);
            preparedStatement.setString(2,fileType);
            preparedStatement.setInt(3,year);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                result.add(String.valueOf(id));
                String fileRootPath = resultSet.getString(2);
                result.add(fileRootPath);
                String filesName = resultSet.getString(3);
                String[] filesNameList = filesName.split(",",-1);
                for(String name:filesNameList){
                    result.add(name);
                }
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void deleteFilePaths(String userID, String fileType, int year){
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteFilePathsSql);
            preparedStatement.setString(1,userID);
            preparedStatement.setString(2,fileType);
            preparedStatement.setInt(3,year);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // For Test
    public static void main(String args[]){
        ApplyFilesDAO dao = new ApplyFilesDAO();
        String userID = "1800011111";
        String fileType = "项目申报";
        int year = 2023;
        String fileRootPath = "researchEnroll/"+String.valueOf(year)+"/"+fileType+"/1800011111-张三/";
        String[] fileNameList = {"1.pdf","","3.xlsx","","","6.docx","","","9.pptx","10.txt"};

//        if(dao.getFilePaths(userID,fileType,year).size()!=0){
//            dao.deleteFilePaths(userID,fileType,year);
//        }
//
//        dao.insertFilePaths(userID,fileType,year,fileRootPath,fileNameList);

        ArrayList<String> result = dao.getFilePaths(userID,fileType,year);
        System.out.println(result.size());
        for(int i = 0 ; i < result.size(); i++){
            System.out.println(result.get(i));
        }
        fileNameList[1] = "2.xsxs";

        dao.updateFilePaths(result.get(0),fileNameList);
        result = dao.getFilePaths(userID,fileType,year);
        for(int i = 0 ; i < result.size(); i++){
            System.out.println(result.get(i));
        }
    }
}
