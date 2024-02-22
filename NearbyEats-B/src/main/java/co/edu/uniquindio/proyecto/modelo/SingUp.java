package co.edu.uniquindio.proyecto.modelo;

import java.util.Date;

public class SingUp {

    private String id;
    private String name;
    private String email;
    private String password;
    private Date registationDate;

    public SingUp(String id, String name, String email, String password, Date registationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registationDate = registationDate;
    }

    public SingUp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistationDate() {
        return registationDate;
    }

    public void setRegistationDate(Date registationDate) {
        this.registationDate = registationDate;
    }
}
