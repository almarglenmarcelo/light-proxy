package slfdemo.sidecar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slfdemo.sidecar.springboot.entity.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
