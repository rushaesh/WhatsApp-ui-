package com.example.whatsapp.modalclass;

public class Status {
    private boolean isSeen;

    public Status(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
