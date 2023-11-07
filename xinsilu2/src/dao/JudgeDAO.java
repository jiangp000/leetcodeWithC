package dao;

import domain.Judge;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jinggu on 2019/10/29.
 */
public class JudgeDAO {
    private String getJudgeSql = "select * from judge where user_id=? and password=?;";
    private String getJudgeByUserIdSql = "select * from judge where user_id=?";
    private String getJudgeAll = "select * from judge;";
    private String insertJudge = "insert into judge (user_id, password, username, phone, email, department, is2021Judge) values (?,?,?,?,?,?,?)";
    private String deleteJudge = "delete from judge where user_id=? and username=?";
    private String updateJudgeType = "update judge set is2021Judge=? where user_id=?";
    private String getJudgeType = "select is2021Judge from judge where user_id=?";

    public void updateJudgeType(String userID, int index, String value){
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(getJudgeType);
            preparedStatement1.setString(1,userID);
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                StringBuffer buffer = new StringBuffer(resultSet.getString(1));
                buffer.replace(index-1,index,value);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateJudgeType);
                preparedStatement2.setString(1, buffer.toString());
                preparedStatement2.setString(2,userID);
                preparedStatement2.executeUpdate();
                DBUtil.closePreparedStatement(preparedStatement2);
            }
            DBUtil.closePreparedStatement(preparedStatement1);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteJudge(String userID, String userName){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteJudge);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertJudge(String userID, String userName, String phone, String email, String department) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertJudge);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, department);
            preparedStatement.setString(7, "00000");
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertExternelJudge(String userID, String password, String userName, String phone, String email, String department) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertJudge);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, department);
            preparedStatement.setString(7, "00000");
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Judge> getJudgeAll() {
        ArrayList<Judge> res = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getJudgeAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Judge judge = new Judge();
                judge.setId(resultSet.getInt(1));
                judge.setUserId(resultSet.getString(2));
                judge.setPassword(resultSet.getString(3));
                judge.setUsername(resultSet.getString(4));
                judge.setDepartment(resultSet.getString(8));
                res.add(judge);
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public Judge getJudgeByUserId(String userId) {
        Connection connection = DBUtil.getConnection();
        Judge judge = new Judge();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getJudgeByUserIdSql);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                judge.setId(resultSet.getInt(1));
                judge.setUserId(resultSet.getString(2));
                judge.setPassword(resultSet.getString(3));
                judge.setUsername(resultSet.getString(4));
                judge.setDepartment(resultSet.getString(8));
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    public Judge getJudge(String userId, String pwd) {
        Connection connection = DBUtil.getConnection();
        Judge judge = new Judge();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getJudgeSql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, pwd);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                judge.setId(resultSet.getInt(1));
                judge.setUserId(resultSet.getString(2));
                judge.setPassword(resultSet.getString(3));
                judge.setUsername(resultSet.getString(4));
                judge.setDepartment(resultSet.getString(8));
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    public static void main(String args[]) {

        JudgeDAO dao = new JudgeDAO();

        Judge judge = dao.getJudge("123", "123");
        if (judge != null) {
            System.out.println(judge.getUsername());
        }
        ArrayList<Judge> res = dao.getJudgeAll();
        for(int i = 0; i < res.size(); i++){
            System.out.println(res.get(i).getUsername());
            System.out.println(res.get(i).getDepartment());
        }

    }


}
