package dao;

import domain.Train;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/28.
 */
public class TrainDAO {
    private String insertTrainSql = "insert into train (train_time,begin_time,end_time,title,content) values (?,?,?,?,?) ";
    private String updateTrainSql = "update train set train_time=?, begin_time=?, end_time=?, title=?, content=? where id = ?";
    private String deleteTrainSql = "delete from train where id = ?";
    private String getTrainByIdSql = "select * from train where id= ?";
    private String getTrainListByYearSql = "select * from train where DATE_FORMAT(train_time,'%Y') = ?";
    private String getYearsFromTrainSql =
            "select distinct DATE_FORMAT(train_time,'%Y') from train order by DATE_FORMAT(train_time,'%Y');";
    private String getValidTrainListSql = "select * from train where train_time > now() order by train_time;";

    public void insertTrain(Train train) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertTrainSql);
            setTrainToPreparedStatement(preparedStatement, train);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTrain(Train train) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateTrainSql);
            setTrainToPreparedStatement(preparedStatement, train);
            preparedStatement.setInt(6, train.getId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTrain(int id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteTrainSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Train getTrainById(int id) {
        Train train = new Train();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getTrainByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                train = getTrainFromResultSet(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }

    public ArrayList<Train> getTrainListByYear(int year) {
        ArrayList<Train> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getTrainListByYearSql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getTrainFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<Integer> getYearsFromTrain() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getYearsFromTrainSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Train> getValidTrainList() {
        ArrayList<Train> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getValidTrainListSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getTrainFromResultSet(resultSet));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    private void setTrainToPreparedStatement(PreparedStatement preparedStatement, Train train) throws SQLException {
        preparedStatement.setDate(1, train.getTrainTime());
        preparedStatement.setString(2, train.getBeginTime());
        preparedStatement.setString(3, train.getEndTime());
        preparedStatement.setString(4, train.getTitle());
        preparedStatement.setString(5, train.getContent());
    }


    private Train getTrainFromResultSet(ResultSet resultSet) throws SQLException {
        Train train = new Train();
        train.setId(resultSet.getInt(1));
        train.setTrainTime(resultSet.getDate(2));
        train.setBeginTime(resultSet.getString(3));
        train.setEndTime(resultSet.getString(4));
        train.setTitle(resultSet.getString(5));
        train.setContent(resultSet.getString(6));
        return train;
    }

//    public static void main(String args[]) {
//        TrainDAO dao = new TrainDAO();
//
//        Train train = new Train();
//        train.setId(6);
//
//        train.setTitle("培训6");
//        train.setContent("培训内容6");
////        dao.insertTrain(train);
////        dao.updateTrain(train);
////        dao.deleteTrain(6);
//
//
//        ArrayList<Train> list = dao.getValidTrainList();
//        for (int i = 0;i<list.size();i++){
//            System.out.println(list.get(i).getTrainTime());
//        }
//
//
//    }


}
