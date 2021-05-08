package springnativetest.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springnativetest.demo.dtos.EnemyDto;
import springnativetest.demo.services.EnemyService;

@RestController
public class EnemyController {
    private final EnemyService enemyService;

    @Autowired
    public EnemyController(EnemyService enemyService) {
        this.enemyService = enemyService;
    }


    @GetMapping(value = "/")
    public Flux<EnemyDto> getAllEnemies() {
        return enemyService.getAllValidEnemies();
    }

    @GetMapping(value = "/{id}")
    public Mono<EnemyDto> getById(@PathVariable Long id) {
        return enemyService.getValidEnemyById(id);
    }

    @PostMapping(value = "/{name}/{health}")
    public Mono<EnemyDto> createEnemy(@PathVariable int health, @PathVariable String name) {
        return enemyService.createNewEnemy(name, health);
    }

}
