package service;

import dao.FilesDAO;
import domain.Files;
import util.UploadUtil;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class FilesService {
    private FilesDAO filesDAO;

    public FilesService() {
        filesDAO = new FilesDAO();
    }

    public void insertFile(Files file) {
        filesDAO.insertFile(file);
    }

    public void deleteFile(int id) {
        Files file = filesDAO.getFileById(id);
        filesDAO.deleteFile(id);
        UploadUtil.deleteFile(UploadUtil.FilesDIR + file.getFileName());
    }

    public ArrayList<Files> getFileListByType(int type, int start) {
        return filesDAO.getFileListByType(type, start);
    }

    public ArrayList<Files> getFileListByNewsId(int id) {
        return filesDAO.getFileListByNewsId(id);
    }


}
