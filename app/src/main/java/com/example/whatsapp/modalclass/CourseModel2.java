package com.example.whatsapp.modalclass;

public class CourseModel2 {
    private String title;
    private String title1;
    private int title2;
    private int imgid;

    public String getTitle() {
        return title;
    }
    public String getTitle1() {
        return title1;
    }
    public int getTitle2() {
        return title2;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTitle1(String title1) {
        this.title1 = title1;
    }
    public void setTitle2(int title2) {
        this.title2 = title2;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public CourseModel2(String title, int imgid, String title1, int title2) {
        this.title = title;
        this.imgid = imgid;
        this.title1 = title1;
        this.title2 = title2;
    }
}
