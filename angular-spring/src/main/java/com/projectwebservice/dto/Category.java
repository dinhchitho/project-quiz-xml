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
}
