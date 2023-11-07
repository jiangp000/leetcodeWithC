package service;

import dao.TrainEnrollDAO;
import domain.TrainEnroll;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class TrainEnrollService {
    private TrainEnrollDAO trainEnrollDAO;

    public TrainEnrollService() {
        trainEnrollDAO = new TrainEnrollDAO();
    }

    public void insertTrainEnroll(TrainEnroll trainEnroll) {
        trainEnrollDAO.insertTrainEnroll(trainEnroll);
    }

    public ArrayList<TrainEnroll> getTrainEnrollListByTrainId(int trainId) {
        return trainEnrollDAO.getTrainEnrollListByTrainId(trainId);
    }
}
