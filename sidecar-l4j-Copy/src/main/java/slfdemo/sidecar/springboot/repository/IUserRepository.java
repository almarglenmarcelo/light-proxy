package slfdemo.sidecar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slfdemo.sidecar.springboot.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}
