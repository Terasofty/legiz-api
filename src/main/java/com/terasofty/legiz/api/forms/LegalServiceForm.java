package com.terasofty.legiz.api.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LegalServiceForm {
    String title;
    String clientUsername;
    String lawyerUsername;
    String type;
    String description;

    public LegalServiceForm(String title, String clientUsername, String lawyerUsername, String type, String description) {
        this.title = title;
        this.clientUsername = clientUsername;
        this.lawyerUsername = lawyerUsername;
        this.type = type;
        this.description = description;
    }
    public String toJson() {
        return "{ \"title\":\"" + title + "\"," +
                " \"clientUsername\":\"" + clientUsername + "\"," +
                " \"lawyerUsername\":\"" + lawyerUsername + "\"," +
                " \"type\":\"" + type + "\"," +
                " \"description\":\"" + description + "\"}";
    }
}
