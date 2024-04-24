package com.nhnacademy.jsp.beans;

import org.jsoup.Jsoup;

import java.io.Serializable;

public class HtmlBeautifier implements Serializable {

    public HtmlBeautifier() {}

    private String html;

    public String getHtml() {
        return Jsoup.parse(this.html).html();
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
