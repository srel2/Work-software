package crud.repository;

import entity.AllTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllTruckRepository extends JpaRepository<AllTruck, Long> {

}
