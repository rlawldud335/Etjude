package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

//    @Query(value = "select * from film where film_created_date is not null and date_add(film_created_date, interval 7 day) < now()", nativeQuery = true)
//    List<Film> deleteByDate();

}
