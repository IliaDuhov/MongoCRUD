package com.project.springmongo.repository;

import com.project.springmongo.model.TodoDTO;
import com.project.springmongo.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {
    @Aggregation("{ $count: 'todos' }")
    Integer countTotalUsers();

    //Расчет среднего имени пользователя по количеству символов
    @Aggregation("{ $group : { _id : null, avgUsernameLength : { $avg : { $strLenCP : '$username' } } } }")
    Integer getAverageUsernameLength();

    //получение всех имён и email
    @Aggregation("{ $project : { name: 1, cost: 1 }}")
    List<User> findAllNamesAndEmails();

    //вывести всех пользователей у которых имя Gibson
    @Aggregation("{ $match : { 'name' : 'Gibson' } }")
    List<User> findUsersWithNameGibson();

    //Получение уникального имени из базы
    @Aggregation("{ $match: { _id: '$username' } }")
    List<String> findAllUniqueUserNames();

    //Получение всех пользователей с именем, начинающимся на 'S':
    @Aggregation("{ $group : { 'name' : { $regex: '^Y' } } }")
    List<User> findUsersNameStartsWithY();
}
