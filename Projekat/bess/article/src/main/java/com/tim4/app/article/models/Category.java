package com.tim4.app.article.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @NotNull
    @Column(name = "cattitle")
    private String cattitle;

    @NotNull
    @Column(name = "description")
    private String description;

    public Category() {
    }

    public Category(String cattitle, String description) {
        this.cattitle = cattitle;
        this.description = description;
    }

    public String getCattitle() {
        return cattitle;
    }

    public void setCattitle(String cattitle) {
        this.cattitle = cattitle;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
