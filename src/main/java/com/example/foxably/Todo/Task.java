package com.example.foxably.Todo;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Task {
    private String title;
    private LocalDate date;
    private BooleanProperty done;

    public Task(String title, LocalDate date) {
        this.title = title;
        this.date = date;
        this.done = new SimpleBooleanProperty(false);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
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
