package com.example.foxably.Courses;

public class Course {
    private int courseId;
    private String title;
    private String company;
    private String image;
    private String path;
    private String chapter0;
    private String chapter1;
    private String chapter2;
    private String chapter3;
    private String chapter4;
    private String chapter5;
    public Course(int courseId, String title, String company, String image){
        this.courseId=courseId;
        this.company=company;
        this.title=title;
        this.image=image;
    }

    public Course(int courseId, String title, String company, String image, String path, String chapter0,String chapter1, String chapter2, String chapter3, String chapter4, String chapter5){
        this.courseId=courseId;
        this.company=company;
        this.title=title;
        this.image=image;
        this.path=path;
        this.chapter0=chapter0;
        this.chapter1=chapter1;
        this.chapter2=chapter2;
        this.chapter3=chapter3;
        this.chapter4=chapter4;
        this.chapter5=chapter5;
    }

    public Course(){}

    public String getChapter0() {
        return chapter0;
    }

    public String getChapter1() {
        return chapter1;
    }

    public String getChapter2() {
        return chapter2;
    }

    public String getChapter3() {
        return chapter3;
    }

    public String getChapter4() {
        return chapter4;
    }

    public String getChapter5() {
        return chapter5;
    }

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
