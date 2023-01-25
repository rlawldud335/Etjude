package offworkseekers.unnamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private int id;

    @NonNull
    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Work> works = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Story> stories = new ArrayList<>();

    @Builder
    public Category(int id, @NonNull String name, List<Work> works) {
        this.id = id;
        this.name = name;
        this.works = works;
    }
}
