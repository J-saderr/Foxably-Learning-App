package com.example.foxably.Todo;

import java.sql.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Task {
    private int id;
    private String title;
    private Date date;
    private BooleanProperty done;

    private String status;

    public Task(int id, String title, Date date) {
        this.id=id;
        this.title = title;
        this.date = date;
        this.done = new SimpleBooleanProperty(false);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return done.get();
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    @Override
    public String toString() {
        return title + " | " + date.toString() + " | " + (isDone() ? "Done" : "Pending");
    }
}
