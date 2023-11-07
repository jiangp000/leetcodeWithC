package action.project;

import com.opensymphony.xwork2.ActionSupport;
import domain.Activity;
import domain.ProjectEnroll;
import service.ProjectEnrollService;
import util.UploadUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class EnrollAction extends ActionSupport {
    private ProjectEnroll projectEnroll;
    private ArrayList<Activity> list;
    private File uploadFile;
    private String uploadFileFileName;

    private ProjectEnrollService projectEnrollService;

    public EnrollAction() {
        projectEnrollService = new ProjectEnrollService();
    }

    public String execute() {
        return SUCCESS;
    }

    public String submit() {
        String reportName = "项目申请书";
        // 项目申请书为 工号-姓名-项目申请书.xxx
        if (projectEnroll != null && uploadFile != null) {
            String suffix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
            String fileName = projectEnroll.getUserId() + "-" + projectEnroll.getUsername() + "-" +
                    reportName + suffix;
            String filePath = UploadUtil.uploadProjectEnrollFile(fileName, uploadFile);
            projectEnroll.setFilePath(filePath);
            if (projectEnroll.getTitle() == "") {
                projectEnroll.setTitle(reportName);
            }

            projectEnrollService.insertProjectEnroll(projectEnroll);
            return SUCCESS;
        }
        return INPUT;
    }

    public void validateSubmit() {
        if (projectEnroll == null) {
            this.addFieldError("projectEnroll", "报名表不能为空");
            return;
        }
        if (projectEnroll.getUserId().trim().equals(""))
            this.addFieldError("userid", "userId不能为空");
        if (projectEnroll.getUsername().trim().equals(""))
            this.addFieldError("username", "username不能为空");
    }

    public ProjectEnroll getProjectEnroll() {
        return projectEnroll;
    }

    public void setProjectEnroll(ProjectEnroll projectEnroll) {
        this.projectEnroll = projectEnroll;
    }

    public ArrayList<Activity> getList() {
        return list;
    }

    public void setList(ArrayList<Activity> list) {
        this.list = list;
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
