package com.example.androidlabs;

public class ToDoItem {
    private String text;
    private boolean urgent;
    private long id;

    public ToDoItem(String text, boolean urgent, long id) {
        this.text = text;
        this.urgent = urgent;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "text='" + text + '\'' +
                ", urgent=" + urgent +
                ", id=" + id +
                '}';
    }
}
