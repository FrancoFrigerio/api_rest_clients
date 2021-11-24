package ar.com.frigeriofranco.practic.repository;

import ar.com.frigeriofranco.practic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    //@Query("SELECT r from Role r where r.description=?1")
    @Query(value = "SELECT * from roles where description = ?1",nativeQuery = true)
    Role findByDescription(String description);
}
