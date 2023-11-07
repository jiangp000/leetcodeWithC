package action.teacher;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ProjectEnroll;
import domain.ResearchEnroll;
import service.ProjectEnrollService;
import service.ResearchEnrollService;
import util.UploadUtil;

import java.io.File;
import java.sql.Date;
import java.util.Map;

public class InnovationAction extends ActionSupport {

    private ProjectEnroll projectEnroll;
    private ResearchEnroll researchEnroll;
    private File[] uploadFile;
    private String[] uploadFileFileName;
    private ProjectEnrollService projectEnrollService;
    private ResearchEnrollService researchEnrollService;

    private ActionContext context;
    private Map<String, Object> session;

    public InnovationAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        String  teacherId = (String) session.get("teacher");
        String  teacherName = (String) session.get("name");
        //projectEnrollService = new ProjectEnrollService();
        researchEnrollService = new ResearchEnrollService();
        //projectEnroll = projectEnrollService.getEnrollByUserIdAndDate(teacherId, Date.valueOf("2020-07-01"));
        researchEnroll =new ResearchEnroll();
        researchEnroll.setUserId(teacherId);
        researchEnroll.setUsername(teacherName);
        //researchEnroll.setTitle(projectEnroll.getTitle());
    }

    public String submitInnovation() {
        return submit("创新大赛");
    }

    private String submit(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String filePath = "";
            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);

                String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();

                filePath += UploadUtil.uploadInnovationFile(fileName, dirName, uploadFile[i]) + ";";
            }
            researchEnroll.setFilePath(filePath);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            researchEnroll.setResearchType(reportName);
            researchEnrollService.updateResearchEnroll(researchEnroll);
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
