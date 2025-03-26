package com.example.whatsapp.modalclass;

public class CourseModel {
    private String title;
    private String title1;
    private String title2;
    private int imgid;

    public String getTitle() {
        return title;
    }
    public String getTitle1() {
        return title1;
    }
    public String getTitle2() {
        return title2;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTitle1(String title1) {
        this.title1 = title1;
    }
    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public CourseModel(String title, int imgid, String title1,String title2) {
        this.title = title;
        this.imgid = imgid;
        this.title1 = title1;
        this.title2 = title2;
    }
}
