package org.example;

import java.util.List;

public class titleJson {
private List<String>  description;

    public titleJson() {
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "titleJson{" +
                "description=" + description +
                '}';
    }
}
