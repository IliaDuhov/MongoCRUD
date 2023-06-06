package com.project.springmongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    @Indexed
    private ERole name;

    public Role(){}

    public Role(ERole name) {
        this.name = name;
    }
}
