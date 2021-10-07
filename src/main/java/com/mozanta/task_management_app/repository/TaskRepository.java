package com.mozanta.task_management_app.repository;

        import org.springframework.data.mongodb.repository.MongoRepository;

        import com.mozanta.task_management_app.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

}