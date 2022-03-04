package com.example.managementlibrary.common;

public class FileInfo {
    private String name;
    private String url;
    private String contentType;

    public FileInfo(String name, String url,String contentType) {
        this.name = name;
        this.url = url;
        this.contentType=contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}