package action.train;

import com.opensymphony.xwork2.ActionSupport;
import domain.Train;
import domain.TrainEnroll;
import service.TrainEnrollService;
import service.TrainService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class EnrollAction extends ActionSupport {
    public String trainIds;
    public TrainEnroll trainEnroll;
    public ArrayList<Train> list;
    private TrainEnrollService trainEnrollService;
    private TrainService trainService;

    public EnrollAction() {
        trainEnrollService = new TrainEnrollService();
        trainService = new TrainService();
    }

    public String execute() {
        // 获取大于当前时间的培训
        list = trainService.getValidTrainList();
        return SUCCESS;
    }

    public String submit() {
        if (trainIds.trim().equals("") || trainEnroll == null) {
            return INPUT;
        }
        String[] ids = trainIds.split(",");
        for (int i = 0; i < ids.length; i++) {
            trainEnroll.setTrainId(Integer.valueOf(ids[i].trim()));
            trainEnrollService.insertTrainEnroll(trainEnroll);
        }
        return SUCCESS;
    }

    public void validateSubmit() {
        if (trainIds.trim().equals("")) {
            this.addFieldError("trainids", "未选择任何培训");
            return;
        }
        if (trainEnroll == null) {
            this.addFieldError("trainEnroll", "培训申请表不能为空");
            return;
        }
        if (trainEnroll.getUserId().trim().equals(""))
            this.addFieldError("userid", "userId is required");
        if (trainEnroll.getUsername().trim().equals(""))
            this.addFieldError("username", "username is required");
        if (trainEnroll.getEmail().trim().equals(""))
            this.addFieldError("email", "email is required");

    }

    public String getTrainIds() {
        return trainIds;
    }

    public void setTrainIds(String trainIds) {
        this.trainIds = trainIds;
    }

    public TrainEnroll getTrainEnroll() {
        return trainEnroll;
    }

    public void setTrainEnroll(TrainEnroll trainEnroll) {
        this.trainEnroll = trainEnroll;
    }
}
