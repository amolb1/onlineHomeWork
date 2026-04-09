package org.college.repo;

import org.college.model.Homework;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeworkRepository extends MongoRepository<Homework, String> {
}