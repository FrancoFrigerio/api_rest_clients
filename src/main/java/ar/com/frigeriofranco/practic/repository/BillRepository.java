package ar.com.frigeriofranco.practic.repository;
import ar.com.frigeriofranco.practic.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {


   // @Query("select b from Bill b join b.cliente c where b.created_At between '2021-10-01' and '2021-11-01' ")
    @Query("select b from Bill b join b.cliente c where b.created_At between ?1 and ?2 ")
    Page<Bill> findAll(Pageable page, Date desde, Date hasta);

    @Query(value = "SELECT b.*, c.id,c.name, c.surname FROM bills b join clients c on b.cliente_id=c.id where b.created_at between ?1 and ?2", nativeQuery = true)
    List <Bill> fetchAllWithClient(String desde,String hasta);



}
