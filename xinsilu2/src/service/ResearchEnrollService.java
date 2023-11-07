package service;

import dao.ResearchEnrollDAO;
import domain.ResearchEnroll;
import domain.Teacher;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class ResearchEnrollService {
    private ResearchEnrollDAO researchEnrollDAO;

    public ResearchEnrollService() {
        researchEnrollDAO = new ResearchEnrollDAO();
    }

    public ArrayList<Teacher> getResearchApplicantAll(){
        return researchEnrollDAO.getResearchApplicantAll();
    }
    public ArrayList<ResearchEnroll> getResearchEnrollByYearAndType(String researchType, String year){
        return researchEnrollDAO.getResearchEnrollByYearAndType(researchType,year);
    }
    public ArrayList<ResearchEnroll> getInnoResearchEnrollByYearAndType(String researchType, String year){
        return researchEnrollDAO.getInnoResearchEnrollByYearAndType(researchType,year);
    }
    public void updateResearchEnrollSaveStatus(String uid,String researchType,String year){
        researchEnrollDAO.updateResearchEnrollSaveStatus(uid,researchType,year);
    }
    public void insertResearchEnrollData(ResearchEnroll researchEnroll) {
        researchEnrollDAO.insertResearchEnrollData(researchEnroll);
    }
    public void updateSubmitSave(int id, String submitSave){
        researchEnrollDAO.updateSubmitSave(id,submitSave);
    }
    public void updateResearchEnrollData(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateResearchEnrollData(researchEnroll);
    }
    public void updateApplyPreResearchEnroll(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateAppPreResearchEnroll(researchEnroll);
    }
    public void updateInnovationPreResearchEnroll(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateInnoPreResearchEnroll(researchEnroll);
    }
    public void updatePreResearchEnroll(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updatePreResearchEnroll(researchEnroll);
    }
    public String getApplyBeforeData(String userName, String userId) {
        return researchEnrollDAO.getApplyBeforeData(userName, userId);
    }
    public String getFinalBeforeData(String userName, String userId) {
        return researchEnrollDAO.getFinalBeforeData(userName, userId);
    }
    public void updateResearchEnroll(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateResearchEnroll(researchEnroll);
    }
    public void updateApplyResearchEnroll(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateApplyResearchEnroll(researchEnroll);
    }

    public void updateResearchEnrollPre(ResearchEnroll researchEnroll) {
        researchEnrollDAO.updateResearchEnrollPre(researchEnroll);
    }

    public void updateResearchEnrollScore(int score, int id) {
        researchEnrollDAO.updateResearchEnrollScore(score, id);
    }

    public ResearchEnroll getResearchEnrollById(int id) {
        return researchEnrollDAO.getResearchEnrollById(id);
    }

    public ResearchEnroll getResearchEnrollById(String userId,String researchType) {
        return researchEnrollDAO.getResearchEnrollByUserId(userId,researchType);
    }

    public int getResearchEnrollIdById(String userId,String researchType) {
        return researchEnrollDAO.getResearchEnrollIdByUserId(userId,researchType);
    }

    public ArrayList<ResearchEnroll> getResearchEnrollListRecent(){
        return researchEnrollDAO.getResearchEnrollListRecent();
    }

    public ArrayList<ResearchEnroll> getResearchEnrollListByDate(Date date){
        return researchEnrollDAO.getResearchEnrollListByDate(date);
    }

    public ArrayList<ResearchEnroll> getResearchEnrollListByType(String researchType){
        return researchEnrollDAO.getResearchEnrollListByType(researchType);
    }
    public ArrayList<ResearchEnroll> getResearchPaperListByType(String researchType){
        return researchEnrollDAO.getResearchPapListByType(researchType);
    }
    public ArrayList<ResearchEnroll> getResearchFinalListByType(String researchType){
        return researchEnrollDAO.getResearchFinListByType(researchType);
    }

    public ArrayList<ResearchEnroll> getResearchApplyInfoByType(String researchType, int year){
        return researchEnrollDAO.getResearchApplyInfoByType(researchType, year);
    }

    public ArrayList<ResearchEnroll> getResearchEnrollListByApply(String researchType){
        return researchEnrollDAO.getResearchEnrollListByApply(researchType);
    }

    public ArrayList<ResearchEnroll> getResearchEnrollListByYear(int year) {
        return researchEnrollDAO.getResearchEnrollListByYear(year);

    }

    public ArrayList<ResearchEnroll> getResearchEnrollListByYearType(int year) {
        return researchEnrollDAO.getResearchEnrollListByYear(year);

    }

    public String getSubmitStage(String year,String researchType) {
        return researchEnrollDAO.getSubmitStageBystage(year, researchType);
    }

    public String getScoreStage(String researchType) {
        return researchEnrollDAO.getScoreStageBystage(researchType);
    }

    public ArrayList<Integer> getYearsFromResearchEnroll() {
        return researchEnrollDAO.getYearsFromResearchEnroll();
    }
}
