package com.projectwebservice.dto;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        "title",
        "description"
})

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
