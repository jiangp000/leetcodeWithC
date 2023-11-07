package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import service.FilesService;
import util.UploadUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class FilesAction extends ActionSupport {
    private ArrayList<Files> list;
    private File uploadFile;
    private String uploadFileFileName;
    private Files file;
    private int newsId;
    private int startPage;

    private FilesService filesService;

    public FilesAction() {
        filesService = new FilesService();
        startPage = 1;
    }

    public String files() {
        list = filesService.getFileListByType(0, startPage);
        return SUCCESS;
    }

    public String addFile() {
        if (uploadFile != null) {
            String filePath = UploadUtil.uploadFile(uploadFileFileName, uploadFile);
            Files file = new Files();
            file.setFileName(uploadFileFileName);
            file.setFilePath(filePath);
            if (newsId != 0)
                file.setNewsId(newsId);
            filesService.insertFile(file);
            return SUCCESS;
        }
        return INPUT;
    }

    public String deleteFile() {
        if (file.getId() == 0) {
            return INPUT;
        } else {
            filesService.deleteFile(file.getId());
            return SUCCESS;
        }
    }


    public ArrayList<Files> getList() {
        return list;
    }

    public void setList(ArrayList<Files> list) {
        this.list = list;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public Files getFile() {
        return file;
    }

    public void setFile(Files file) {
        this.file = file;
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
