package co.edu.uniquindio.proyecto.modelo;

import java.util.Date;

public class Comentary {

    private String id;
    private String content;
    private Date publicationDate;
    private User user;
    private Location location;

    public Comentary(String id, String content, Date publicationDate, User user, Location location) {
        this.id = id;
        this.content = content;
        this.publicationDate = publicationDate;
        this.user = user;
        this.location = location;
    }

    public Comentary() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
