package com.project.springmongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;
    private String todo;
    private String description;
    @Indexed
    private Boolean completed;
    @Indexed
    private Date createAt;
    @Indexed
    private Date updateAt;
}
