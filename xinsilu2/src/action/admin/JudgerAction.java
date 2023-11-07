package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;
import domain.Files;
import domain.Judge;
import service.FilesService;
import service.JudgeService;
import util.UploadUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgerAction extends ActionSupport {
    private String judgerId;
    private String judgerName;
    private String judgerDepartment;
    private String judgerPhone;
    private String judgerMail;
    private String researchType;
    private String judgeType;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    private ArrayList<String> judgeList;
    private JudgeService judgeService;
    public JudgerAction() {
        judgeService = new JudgeService();
        judgeList = new ArrayList<>();
    }
    public String addJudgerJSP() {
        return SUCCESS;
    }
    public String addJudge(){
        System.out.println(judgerId);
        if(judgerId==null) return SUCCESS;
        if(judgerPhone==null) judgerPhone="";
        if(judgerMail==null) judgerMail="";
        judgeService.insertJudge(judgerId,judgerName,judgerPhone,judgerMail,judgerDepartment);
        return SUCCESS;
    }

    public String addExternelJudgeJSP() {return SUCCESS;}
    public String addExternelJudge(){
        // if(judgerId==null) return SUCCESS;
        judgerPhone = "";
        judgerMail = "";
        judgerDepartment = "";
        System.out.println(judgerId);
        System.out.println(judgerName);
        System.out.println(password);
        judgeService.insertExternelJudge(judgerId,password,judgerName,judgerPhone,judgerMail,judgerDepartment);
        return SUCCESS;
    }

    public String removeJudgerJSP(){
        ArrayList<Judge> judges = judgeService.getJudgeAll();
        for(Judge judge:judges){
            if(judge.getDepartment()==null){
                String judgeStr = judge.getUsername()+" "+judge.getUserId();
                judgeList.add(judgeStr);
            }else{
                String judgeStr = judge.getUsername()+" "+judge.getUserId()+" "+judge.getDepartment();
                judgeList.add(judgeStr);
            }
        }
        return SUCCESS;
    }
    public String removeJudge(){
        if(judgerId==null) return SUCCESS;
        String[] judge = judgerId.split(" ");
        if(judge.length<2){
            return SUCCESS;
        }
        String userID = judge[1];
        String userName = judge[0];
        judgeService.deleteJudge(userID,userName);
        return SUCCESS;
    }
    public String ManageJudgeJSP(){
        ArrayList<Judge> judges = judgeService.getJudgeAll();
        for(Judge judge:judges){
            if(judge.getDepartment()==null){
                String judgeStr = judge.getUsername()+" "+judge.getUserId();
                judgeList.add(judgeStr);
            }else{
                String judgeStr = judge.getUsername()+" "+judge.getUserId()+" "+judge.getDepartment();
                judgeList.add(judgeStr);
            }
        }
        return SUCCESS;
    }
    public String ManageJudge(){
        if(judgerId==null) return SUCCESS;
        String[] judge = judgerId.split(" ");
        if(judge.length<2){
            return SUCCESS;
        }
        String userID = judge[1];
        int offset = 0;
        switch(researchType){
            case "项目申报":{
                offset = 1;
                break;
            }
            case "创新大赛":{
                offset = 2;
                break;
            }
            case "中期报告":{
                offset = 3;
                break;
            }
            case "教研论文":{
                offset = 4;
                break;
            }
            case "结题报告":{
                offset = 5;
                break;
            }
        }
        String value = "0";
        switch(judgeType){
            case "非评委":{
                value = "0";
                break;
            }
            case "重点优先项目评委":{
                if(offset==2) return SUCCESS;
                value = "2";
                break;
            }
            case "一般项目评委":{
                if(offset==2) return SUCCESS;
                value = "1";
                break;
            }
            case "仅网评评委":{
                if(offset!=2) return SUCCESS;
                value = "1";
                break;
            }
            case "仅现评评委":{
                if(offset!=2) return SUCCESS;
                value = "3";
                break;
            }
            case "网评和现评评委":{
                if(offset!=2) return SUCCESS;
                value = "2";
                break;
            }
        }
        judgeService.updateJudgeType(userID,offset,value);
        return SUCCESS;
    }


    public String getJudgerId() {
        return judgerId;
    }

    public void setJudgerId(String judgerId) {
        this.judgerId = judgerId;
    }

    public String getJudgerName() {
        return judgerName;
    }

    public void setJudgerName(String judgerName) {
        this.judgerName = judgerName;
    }

    public String getJudgerDepartment() {
        return judgerDepartment;
    }

    public void setJudgerDepartment(String judgerDepartment) {
        this.judgerDepartment = judgerDepartment;
    }

    public String getJudgerPhone() {
        return judgerPhone;
    }

    public void setJudgerPhone(String judgerPhone) {
        this.judgerPhone = judgerPhone;
    }

    public String getJudgerMail() {
        return judgerMail;
    }

    public void setJudgerMail(String judgerMail) {
        this.judgerMail = judgerMail;
    }

    public ArrayList<String> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(ArrayList<String> judgeList) {
        this.judgeList = judgeList;
    }
    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }
    public void setJudgeType(String judgeType) {
        this.judgeType = judgeType;
    }
    public String getResearchType() {
        return researchType;
    }

    public String getJudgeType() {
        return judgeType;
    }

}
