package com.pivotal.bookmarksClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Bookmark {

    private Integer id;

    private String name;

    private Date added;

    private URL url;

    public Bookmark() {
    }

    public Bookmark(Integer id, String name, Date added, String stringUrl) {
        this.id = id;
        this.name = name;
        this.added = added;
        try {
            this.url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}

