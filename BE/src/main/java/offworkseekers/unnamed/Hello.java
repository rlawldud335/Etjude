package offworkseekers.unnamed;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@ToString
public class Hello {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

}

