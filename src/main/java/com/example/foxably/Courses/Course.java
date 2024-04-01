package com.example.foxably.Courses;

public class Course {
    private int courseId;
    private String title;
    private String company;
    private String image;
    private String path;
    public Course(int courseId, String title, String company, String image){
        this.courseId=courseId;
        this.company=company;
        this.title=title;
        this.image=image;
    }

    public Course(String title, String company, String image){
        this.company=company;
        this.title=title;
        this.image=image;
    }

    public Course(){}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
