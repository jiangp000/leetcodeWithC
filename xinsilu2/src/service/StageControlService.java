package service;

import dao.StageControlDAO;
import domain.StageControl;

/**
 * Created by widesui on 20/7/13.
 */
public class StageControlService {
    private StageControlDAO stageControlDao;

    public StageControlService() {

        stageControlDao = new StageControlDAO();
    }


    public void updateScoreStageByStage(StageControl stageControl) {
        stageControlDao.updateScoreStage(stageControl);
    }

    public void updateSubmitStageByStage(StageControl stageControl) {
        stageControlDao.updateSubmitStage(stageControl);
    }






}
