package springnativetest.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("enemies")
@Getter @Setter
@AllArgsConstructor
public class Enemy {
    @Id
    private Long id;
    private String name;
    private int health;
}
