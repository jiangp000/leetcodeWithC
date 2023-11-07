package domain;

/**
 * Created by jinggu on 19/4/28.
 */
public class Files {
    private int id;
    private int newsId = -1;
    private String fileName;
    private String filePath;

    /*type: 0指文件,1指软件安装包*/
    private int type = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", newsId=" + newsId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", type=" + type +
                '}';
    }
}
