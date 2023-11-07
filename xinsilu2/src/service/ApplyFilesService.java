package service;

import dao.ApplyFilesDAO;

import java.util.ArrayList;

public class ApplyFilesService {
    private ApplyFilesDAO applyFilesDAO;
    public ApplyFilesService(){applyFilesDAO = new ApplyFilesDAO();}
    public void insertFilePaths(String userID, String fileType, int year, String fileRootPath, String[] fileNameList){
        applyFilesDAO.insertFilePaths(userID,fileType,year,fileRootPath,fileNameList);
    }
    public void updateFilePaths(String id, String[] fileNameList){
        applyFilesDAO.updateFilePaths(id,fileNameList);
    }
    public ArrayList<String> getFilePaths(String userID, String fileType, int year){
        return applyFilesDAO.getFilePaths(userID,fileType,year);
    }
    public void deleteFilePaths(String userID, String fileType, int year){
        applyFilesDAO.deleteFilePaths(userID,fileType,year);
    }
}
