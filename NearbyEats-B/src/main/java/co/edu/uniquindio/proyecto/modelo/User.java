package co.edu.uniquindio.proyecto.modelo;

import java.util.Date;

public class User extends SingUp {

    private String profilePicture;
    private String cityOfReside;
    private Date registationDate;

    public User(String id, String name, String email, String password, Date registationDate, String profilePicture, String cityOfReside, Date registationDate1) {
        super(id, name, email, password, registationDate);
        this.profilePicture = profilePicture;
        this.cityOfReside = cityOfReside;
        this.registationDate = registationDate1;
    }

    public User(String profilePicture, String cityOfReside, Date registationDate) {
        this.profilePicture = profilePicture;
        this.cityOfReside = cityOfReside;
        this.registationDate = registationDate;
    }

    public User(String id, String name, String email, String password, Date registationDate) {
        super(id, name, email, password, registationDate);
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCityOfReside() {
        return cityOfReside;
    }

    public void setCityOfReside(String cityOfReside) {
        this.cityOfReside = cityOfReside;
    }

    @Override
    public Date getRegistationDate() {
        return registationDate;
    }

    @Override
    public void setRegistationDate(Date registationDate) {
        this.registationDate = registationDate;
    }
}
