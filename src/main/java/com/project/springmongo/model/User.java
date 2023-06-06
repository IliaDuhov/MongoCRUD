package com.project.springmongo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank
    @Size(max = 40)
    @Indexed
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @DBRef
    @Indexed
    private Set<Role> roles = new HashSet<>();
    /*
        DBRef-ы похожи на ссылки, приведенные вручную, в том смысле, что они также содержат _id документа,
        на который ссылается ссылка. Однако они содержат коллекцию документов, на которые дана ссылка,
        в $ref и, необязательно, его базу данных, а также в $db.
        Преимущество этого по сравнению со ссылками вручную заключается в том, что оно разъясняет,
        на какую коллекцию мы ссылаемся.
    */

    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
