package service;

import dao.JudgeDAO;
import domain.Judge;

import java.util.ArrayList;

/**
 * Created by jinggu on 2019/10/29.
 */
public class JudgeService {
    private JudgeDAO judgeDAO;

    public JudgeService() {
        judgeDAO = new JudgeDAO();
    }

    public Judge getJudge(String userId, String pwd) {
        return judgeDAO.getJudge(userId, pwd);
    }

    public Judge getJudgeByUserId(String userId) {
        return judgeDAO.getJudgeByUserId(userId);
    }

    public ArrayList<Judge> getJudgeAll() {return judgeDAO.getJudgeAll();}
    public void insertExternelJudge(String userID, String password, String userName, String phone, String email, String department) {
        judgeDAO.insertExternelJudge(userID, password, userName,phone,email,department);
    }
    public void insertJudge(String userID, String userName, String phone, String email, String department){judgeDAO.insertJudge(userID, userName, phone, email, department);}
    public void deleteJudge(String userID, String userName){judgeDAO.deleteJudge(userID,userName);}
    public void updateJudgeType(String userID, int index, String value){judgeDAO.updateJudgeType(userID,index,value);}
}
