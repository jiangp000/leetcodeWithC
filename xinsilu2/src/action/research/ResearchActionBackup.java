package action.research;

import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import service.ResearchEnrollService;
import util.UploadUtil;

import java.io.File;
import java.util.Calendar;

public class ResearchActionBackup extends ActionSupport {
    private ResearchEnroll researchEnroll;
    private File[] uploadFile;
    private String[] uploadFileFileName;
    private ResearchEnrollService researchEnrollService;


    public ResearchActionBackup() {

        researchEnrollService = new ResearchEnrollService();
    }

    public String submitApp() {
        return submitResearchApply("项目申报");
    }

    public String submitApplyPre() {
        return submitAppPre("项目申报");
    }

    public String submitInno() {
        return submitresearch("创新大赛");
    }


    public String submitMid() {
        return submitresearch("中期报告");
    }

    public String submitPap() {
        return submitresearch("教研论文");
    }

    public String submitFin() {
        return submitresearch("结题报告");
    }

    public String submitFinalPre() {
        return submitFinpre("结题报告");
    }


    private String submitresearch(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteOldFile(dirName, reportName);
            }
            String filePath = "";
            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName) + ";";

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }


            researchEnroll.setResearchType(reportName);
            /*if(researchEnroll.getResearchType().equals("创新大赛")){
                if(researchEnroll.getProjectApplySort().equals("")){
                    researchEnroll.setProjectApplySort("无");
                }
            }else{
                if(researchEnroll.getProjectNo().equals("")){
                    researchEnroll.setProjectNo("无");
                }
            }*/

            researchEnrollService.insertResearchEnrollData(researchEnroll);


            return SUCCESS;
        }
        return INPUT;
    }

    //项目申请，最初开始阶段没有项目编号
    private String submitResearchApply(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteOldFile(dirName, reportName);
            }
            String filePath = "";
            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName) + ";";

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }
            Calendar date = Calendar.getInstance();
            //String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            /*if(researchEnroll.getProjectNo().equals("")){
                researchEnroll.setProjectNo("无");
            }*/
            researchEnroll.setResearchType(reportName);
            researchEnrollService.insertResearchEnrollData(researchEnroll);


            return SUCCESS;
        }
        return INPUT;
    }

    //提交项目申报现场汇报PPT和PDF
    private String submitAppPre(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        //System.out.println(researchEnroll.getUserId()+"***" + researchEnroll.getUsername());
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteApplyPreOldFile(dirName, reportName);
            }
            String filePath = "";

            String applyBeforeData = researchEnrollService.getApplyBeforeData(
                    researchEnroll.getUsername(),researchEnroll.getUserId());

            if(applyBeforeData.split("]").length > 2){
                researchEnroll.setTitle(applyBeforeData.split("]")[0]);
                filePath = applyBeforeData.split("]")[1];
                String filePathBefore = applyBeforeData.split("]")[1];
                if(filePathBefore.contains("}")){
                    filePath = filePathBefore.substring(0, filePathBefore.indexOf("}"));
                }
            }


            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += "}" + UploadUtil.uploadResearchAppFile(fileName, dirName, uploadFile[i], reportName);

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }
            Calendar date = Calendar.getInstance();
            //String year = String.valueOf(date.get(Calendar.YEAR));
            //String year = "2022";
            String zipFilePathName = UploadUtil.compressApplyPreFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            /*if(researchEnroll.getProjectNo().equals("")){
                researchEnroll.setProjectNo("无");
            }*/
            researchEnroll.setResearchType(reportName);
            researchEnrollService.updateApplyPreResearchEnroll(researchEnroll);


            return SUCCESS;
        }
        return INPUT;
    }

    private String submitFinpre(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteFinalePreOldFile(dirName, reportName);
            }
            String filePath = "";
            String finalBeforeData = researchEnrollService.getFinalBeforeData(
                    researchEnroll.getUsername(),researchEnroll.getUserId());
            researchEnroll.setTitle(finalBeforeData.split("]")[0]);
            researchEnroll.setProjectNo(finalBeforeData.split("]")[2]);
            researchEnroll.setDeptName(finalBeforeData.split("]")[4]);
            researchEnroll.setProjectSort(finalBeforeData.split("]")[5]);
            researchEnroll.setTeamMember(finalBeforeData.split("]")[6]);
            researchEnroll.setId(Integer.parseInt(finalBeforeData.split("]")[7]));
            filePath = finalBeforeData.split("]")[1];
            String filePathBefore = finalBeforeData.split("]")[1];
            if(filePathBefore.contains("}")){
                filePath = filePathBefore.substring(0, filePathBefore.indexOf("}"));
            }


            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += "}" + UploadUtil.uploadResearchPreFile(fileName, dirName, uploadFile[i], reportName);

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }


            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFinalePreFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),year,researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);

            researchEnroll.setResearchType(reportName);
            //researchEnrollService.insertResearchEnroll(researchEnroll);
            researchEnrollService.updatePreResearchEnroll(researchEnroll);


            return SUCCESS;
        }
        return INPUT;
    }


    public ResearchEnroll getResearchEnroll() {
        return researchEnroll;
    }

    public void setResearchEnroll(ResearchEnroll researchEnroll) {
        this.researchEnroll = researchEnroll;
    }

    public File[] getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String[] getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String[] uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }
}
