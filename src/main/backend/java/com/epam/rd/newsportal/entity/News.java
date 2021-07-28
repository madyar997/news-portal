package com.epam.rd.newsportal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String createdDate;
    @Column(name="text", columnDefinition="TEXT")
    private String Text;

    public News() {

    }

    public News(Long id, String title, String createdDate, String text) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        Text = text;
    }

    //TODO fix Text data type
    public News(String title, String createdDate, String text) {
        this.title = title;
        this.createdDate = createdDate;
        Text = text;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(createdDate, news.createdDate) && Objects.equals(Text, news.Text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdDate, Text);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", Text='" + Text + '\'' +
                '}';
    }
}
