package action.research;

import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import service.ResearchEnrollService;
import util.UploadUtil;

import java.io.File;

/**
 * Created by jinggu on 19/4/29.
 * Changed by HongweiHe on 21/6/14.
 */
public class EnrollAction extends ActionSupport {
    private ResearchEnroll researchEnroll;
    private File uploadFile;
    private String uploadFileFileName;
    private ResearchEnrollService researchEnrollService;
//    private String msg;


    public EnrollAction() {
        researchEnrollService = new ResearchEnrollService();
    }

    public String submitMid() {
        return submit("中期报告");
    }

    public String submitPaper() {
        return submittest("教研论文");
    }

    public String submitFinal() {
        return submit("结题报告");
    }


    private String submit(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String suffix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")); //文件后缀
            String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
                    reportName + suffix; //文件名
            // 上传
            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);

            researchEnroll.setFilePath(filePath);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            researchEnroll.setResearchType(reportName);
            researchEnrollService.insertResearchEnrollData(researchEnroll);
            return SUCCESS;
        }
        return INPUT;
    }



    private String submittest(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String suffix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")); //文件后缀
            String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
                    reportName + suffix; //文件名
            // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();

            String filePath = UploadUtil.uploadInnovationFile(fileName, dirName, uploadFile);

            researchEnroll.setFilePath(filePath);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            researchEnroll.setResearchType(reportName);
            researchEnrollService.insertResearchEnrollData(researchEnroll);
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

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }



    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

}
