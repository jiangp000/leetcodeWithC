package dao;

import domain.TrainEnroll;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/28.
 */
public class TrainEnrollDAO {
    private String deleteEnrollSql = "delete from train_enroll where train_id = ? and user_id = ?;";
    private String insertEnrollSql = "insert into train_enroll (train_id,user_id,user_name,email,phone) values (?,?,?,?,?);";
    private String getEnrollListByTrainIdSql = "select * from train_enroll where train_id = ? ";

    public void insertTrainEnroll(TrainEnroll trainEnroll) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEnrollSql);
            preparedStatement.setInt(1, trainEnroll.getTrainId());
            preparedStatement.setString(2, trainEnroll.getUserId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertEnrollSql);
            preparedStatement.setInt(1, trainEnroll.getTrainId());
            preparedStatement.setString(2, trainEnroll.getUserId());
            preparedStatement.setString(3, trainEnroll.getUsername());
            preparedStatement.setString(4, trainEnroll.getEmail());
            preparedStatement.setString(5, trainEnroll.getPhone());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TrainEnroll> getTrainEnrollListByTrainId(int trainId) {
        ArrayList<TrainEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getEnrollListByTrainIdSql);
            preparedStatement.setInt(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getTrainEnrollFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private TrainEnroll getTrainEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        TrainEnroll trainEnroll = new TrainEnroll();
        trainEnroll.setId(resultSet.getInt(1));
        trainEnroll.setTrainId(resultSet.getInt(2));
        trainEnroll.setUserId(resultSet.getString(3));
        trainEnroll.setUsername(resultSet.getString(4));
        trainEnroll.setEmail(resultSet.getString(5));
        trainEnroll.setPhone(resultSet.getString(6));
        return trainEnroll;
    }

//    public static void main(String args[]){
//        TrainEnrollDAO dao = new TrainEnrollDAO();
//
//        TrainEnroll trainEnroll = new TrainEnroll();
//        trainEnroll.setTrainId(1);
//        trainEnroll.setUserId("00000623470");
//        trainEnroll.setUsername("教师6");
//        trainEnroll.setEmail("jiaoshi@pku.edu.cn");
//        dao.insertTrainEnroll(trainEnroll);
//
//
//
//
//        ArrayList<TrainEnroll> list = dao.getTrainEnrollListByTrainId(1);
//        for (int i = 0;i<list.size();i++){
//            System.out.println(list.get(i).getUsername());
//        }
//
//
//    }


}
