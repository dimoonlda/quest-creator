package ua.kiev.dimoon.questcreator.front.base.dto;

public class QuestDto {
    private Integer id;
    private String title;
    private String description;

    public Integer getId() {
        return id;
    }

    public QuestDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
