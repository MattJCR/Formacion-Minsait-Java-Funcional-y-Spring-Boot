package com.example.demo.nasa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nasa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;
    @Column(columnDefinition = "LONGTEXT")
    private String explanation;
    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;

    public Nasa(String date, String explanation, String title, String url, String mediaType) {
        this.date = date;
        this.explanation = explanation;
        this.title = title;
        this.url = url;
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Id: " + this.id + " - title: " + this.title + " \n - url: " + this.url;
    }

    protected Nasa() {
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getserviceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public void setmediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setserviceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
