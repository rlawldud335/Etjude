package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
