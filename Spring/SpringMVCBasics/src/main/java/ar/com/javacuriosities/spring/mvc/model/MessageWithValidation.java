package ar.com.javacuriosities.spring.mvc.model;

import javax.validation.constraints.NotNull;

public class MessageWithValidation {

    private String title;

    @NotNull
    private String content;

    public MessageWithValidation() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
