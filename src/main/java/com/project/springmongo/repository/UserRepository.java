package com.project.springmongo.repository;

import com.project.springmongo.model.Role;
import com.project.springmongo.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    //Подсчет количества пользователей в коллекции:
    @Aggregation("{ $count: 'User' }")
    Integer countTotalUsers();

    //Расчет среднего имени пользователя по количеству символов
    @Aggregation("{ $group : { _id : null, avgUsernameLength : { $avg : { $strLenCP : '$username' } } } }")
    Integer getAverageUsernameLength();

    //получение всех имён и email
    @Aggregation("{ $project : { username: 1, email: 1 }}")
    List<User> findAllNamesAndEmails();

    //вывести всех пользователей у которых имя Tom
    @Aggregation("{ $match : { 'username' : 'user' } }")
    List<User> findUsersWithNameUser();

    //Получение уникального имени из базы
    @Aggregation("{ $group: { _id: '$username' } }")
    List<String> findAllUniqueUserNames();

    //Получение всех пользователей с именем, начинающимся на 'S':
    @Aggregation("{ $match : { 'username' : { $regex: '^m' } } }")
    List<User> findUsersNameStartsWithM();

}
