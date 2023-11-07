package action.project;

import com.opensymphony.xwork2.ActionSupport;
import domain.Files;
import service.FilesService;

import java.util.ArrayList;

/**
 * Created by jinggu on 19/4/29.
 */
public class ProjectAction extends ActionSupport {
    private ArrayList<Files> list;
    private int startPage;
    private FilesService filesService;

    public ProjectAction() {
        filesService = new FilesService();
        startPage = 1;
    }

    public String file() {
        list = filesService.getFileListByType(0, startPage);
        if (list.size() == 0) {
            startPage = 1;
            list = filesService.getFileListByType(0, startPage);
        }
        return SUCCESS;
    }

    public String exe() {
        list = filesService.getFileListByType(1, startPage);
        if (list.size() == 0) {
            startPage = 1;
            list = filesService.getFileListByType(1, startPage);
        }
        return SUCCESS;
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
}
