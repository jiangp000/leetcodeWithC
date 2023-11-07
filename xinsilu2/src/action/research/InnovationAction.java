package action.research;

import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import service.ResearchEnrollService;
import util.UploadUtil;

import java.io.File;

public class InnovationAction extends ActionSupport {
    private ResearchEnroll researchEnroll;
    private File[] uploadFile;
    private String[] uploadFileFileName;
    private ResearchEnrollService researchEnrollService;

    public InnovationAction() {
        researchEnrollService = new ResearchEnrollService();
    }

    public String submitInnovation() {
        return submit("创新大赛");
    }

    private String submit(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();

                String filePath = UploadUtil.uploadInnovationFile(fileName, dirName, uploadFile[i]);

                researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnrollData(researchEnroll);
            }
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