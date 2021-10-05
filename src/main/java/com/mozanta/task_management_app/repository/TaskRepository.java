package com.mozanta.task_management_app.repository;

        import java.util.List;

        import org.springframework.data.mongodb.repository.MongoRepository;

        import com.mozanta.task_management_app.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByTaskContaining(String task);
    List<Task> findByCompleted(boolean completed);
}