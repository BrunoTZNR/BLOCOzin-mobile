package com.example.blocozin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bloco implements Serializable {
    private Integer id;
    private String title;
    private String desc;

    public Bloco () {};

    public Bloco(Integer id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public Bloco(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Bloco{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
