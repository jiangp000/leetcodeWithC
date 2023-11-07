package domain;

/**
 * Created by jinggu on 19/4/25.
 */
public class TrainEnroll {
    private int id;
    private int trainId;
    private String userId;
    private String username;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toCSVStr() {
        return userId + "," + username + "," + phone + "," + email;
    }


    @Override
    public String toString() {
        return "TrainEnroll{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
