package springnativetest.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import springnativetest.demo.daos.EnemyDao;
import springnativetest.demo.entities.Enemy;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer(){
        return builder ->
                builder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Bean()
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        // This will create our database table and schema
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        // This will drop our table after we are done so we can have a fresh start next run
        initializer.setDatabaseCleaner(new ResourceDatabasePopulator(new ClassPathResource("cleanup.sql")));
        return initializer;
    }
}
