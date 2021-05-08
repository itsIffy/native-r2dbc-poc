package springnativetest.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springnativetest.demo.daos.EnemyDao;
import springnativetest.demo.dtos.EnemyDto;
import springnativetest.demo.entities.Enemy;

@Service
public class EnemyService {
    private final EnemyDao enemyDao;
    private final ObjectMapper objectMapper;

    @Autowired
    public EnemyService(EnemyDao enemyDao, ObjectMapper objectMapper) {
        this.enemyDao = enemyDao;
        this.objectMapper = objectMapper;
    }

    public Flux<EnemyDto> getAllValidEnemies() {
        return enemyDao.findAll().map(enemy -> objectMapper.convertValue(enemy, EnemyDto.class));
    }

    public Mono<EnemyDto> getValidEnemyById(Long id) {
        return enemyDao.findById(id).map(enemy -> objectMapper.convertValue(enemy, EnemyDto.class));
    }

    public Mono<EnemyDto> createNewEnemy(String name, int health) {
        return enemyDao.save(new Enemy(null, name, health)).map(enemy -> objectMapper.convertValue(enemy, EnemyDto.class));
    }
}
