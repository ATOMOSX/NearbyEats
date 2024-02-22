package co.edu.uniquindio.proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Location {

    private String id;
    private String name;
    private String description;
    private Date schedule;
    private ArrayList phoneNumber;
    private Places category;

    //Pendiente hacer la instalación de la Api Rest para el manejo de las imagenes mediante SpringBoot
//    @Lob
    private String images;
    private Score scores;
    private Comentary comments;

    public Location(String id, String name, String description, Date schedule, ArrayList phoneNumber, Places category, String images, Score scores, Comentary comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.images = images;
        this.scores = scores;
        this.comments = comments;
    }

    public Location() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public ArrayList getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(ArrayList phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Places getCategory() {
        return category;
    }

    public void setCategory(Places category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Score getScores() {
        return scores;
    }

    public void setScores(Score scores) {
        this.scores = scores;
    }

    public Comentary getComments() {
        return comments;
    }

    public void setComments(Comentary comments) {
        this.comments = comments;
    }
}
