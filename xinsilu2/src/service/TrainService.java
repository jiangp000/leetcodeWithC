package service;

import dao.TrainDAO;
import domain.Train;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class TrainService {
    private TrainDAO trainDAO;

    public TrainService() {
        trainDAO = new TrainDAO();
    }

    public void insertTrain(Train train) {
        trainDAO.insertTrain(train);
    }

    public void updateTrain(Train train) {
        trainDAO.updateTrain(train);
    }

    public void deleteTrain(int id) {
        trainDAO.deleteTrain(id);
    }

    public Train getTrainById(int id) {
        return trainDAO.getTrainById(id);
    }

    public ArrayList<Train> getTrainListByYear(int year) {
        return trainDAO.getTrainListByYear(year);
    }

    public ArrayList<Integer> getYearsFromTrain() {
        return trainDAO.getYearsFromTrain();
    }

    public ArrayList<Train> getValidTrainList(){
        return trainDAO.getValidTrainList();
    }


}
