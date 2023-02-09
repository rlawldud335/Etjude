package offworkseekers.unnamed.db.entity;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "INT UNSIGNED")
    private Long categoryId;

    @NonNull
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Work> works = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Story> stories = new ArrayList<>();

    @Builder
    public Category(Long categoryId, @NonNull String categoryName, List<Work> works) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.works = works;
    }
}
