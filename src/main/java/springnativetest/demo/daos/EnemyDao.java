package springnativetest.demo.daos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import springnativetest.demo.entities.Enemy;

@Repository
public interface EnemyDao extends ReactiveCrudRepository<Enemy, Long> {
}
