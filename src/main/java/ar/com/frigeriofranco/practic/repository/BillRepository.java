package ar.com.frigeriofranco.practic.repository;
import ar.com.frigeriofranco.practic.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {


    @Query("select b from Bill b join b.cliente c")
    Page<Bill> findAll(Pageable page);

    @Query(value = "SELECT b.*, c.id,c.name, c.surname FROM bills b join clients c on b.cliente_id=c.id", nativeQuery = true)
    List <Bill> fetchAllWithClient();



}
