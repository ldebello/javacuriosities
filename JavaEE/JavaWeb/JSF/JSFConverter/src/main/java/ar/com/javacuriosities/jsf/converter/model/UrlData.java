package ar.com.javacuriosities.jsf.converter.model;

public class UrlData {
    private String url;

    public UrlData() {
    }

    public UrlData(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}