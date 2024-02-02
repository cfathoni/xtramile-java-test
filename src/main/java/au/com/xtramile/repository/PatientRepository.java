package au.com.xtramile.repository;

import au.com.xtramile.model.Patient;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
  Optional<Patient> findById(Long id);

  void deleteById(Long id);

  Patient save(Patient patient);

  @Query("SELECT p FROM Patient p WHERE " +
      "(LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
      "OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :name, '%')))")
  Page<Patient> findByName(@Param("name") String name, Pageable pageable);
}
