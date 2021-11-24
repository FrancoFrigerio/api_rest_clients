package ar.com.frigeriofranco.practic.repository;

import ar.com.frigeriofranco.practic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmail(String email);

    @Query(value = "select sum(b.total) from bills b inner join clients c on c.id = b.cliente_id where c.id=?1", nativeQuery = true)
    Double sumBills(Long id);

}
