package com.example.baitap_tuan2.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Pic {
    @NotBlank(message = "Image ID is required")
    private String id;

    private String url;

    @NotBlank(message = "Alt text is required")
    @Size(min = 5, max = 100, message = "Alt text must be between 5 and 100 characters")
    private String altText;

    private Long fileSize;

    public Pic() {
    }

    public Pic(String id, String url, String altText, Long fileSize) {
        this.id = id;
        this.url = url;
        this.altText = altText;
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Pic [id=" + id + ", url=" + url + ", altText=" + altText + ", fileSize=" + fileSize + "]";
    }
}