package com.example.whatsapp.modalclass;

public class CourseModel3 {
    private String title;
    private String title1;
    private int imgid;

    public String getTitle() {
        return title;
    }
    public String getTitle1() {
        return title1;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public CourseModel3(String title, int imgid, String title1) {
        this.title = title;
        this.imgid = imgid;
        this.title1 = title1;

    }
}
