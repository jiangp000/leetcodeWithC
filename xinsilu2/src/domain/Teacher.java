package domain;

/**
 * Created by jinggu on 19/5/2.
 */
public class Teacher {
    //ProjectEnroll
    private String userId;
    private String username;
    private String department;
    private String email;
    private float founds;
    //ResearchEnroll
    private String title;
    private String filePath;
    //TrainEnroll
//    private String[] trains;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public float getFounds() {
        return founds;
    }

    public void setFounds(float founds) {
        this.founds = founds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", founds=" + founds +
                ", title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
