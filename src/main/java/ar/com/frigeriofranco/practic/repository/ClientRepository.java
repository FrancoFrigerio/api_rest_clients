package ar.com.frigeriofranco.practic.repository;

import ar.com.frigeriofranco.practic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmail(String email);

    @Query(value = "select sum(b.total) from bills b inner join clients c on c.id = b.cliente_id where c.id=?1", nativeQuery = true)
    Double sumBills(Long id);

    @Query(value = "select c.* ,sum(b.total) from clients c inner join bills b on c.id = b.cliente_id group by c.name order by sum(b.total) desc limit 3",nativeQuery = true)
    List<Client> getMetrics (); //clientes que mas gastaron

    @Query(value = "select sum(b.total) from clients c inner join bills b on b.cliente_id = c.id where c.id =?1",nativeQuery = true)
    Double getTotal(Long id);

    @Query(value = "select c.*, count(*) from clients c inner join bills b on c.id = b.cliente_id group by c.name order by count(*) desc limit 3",nativeQuery = true)
    List<Client> getMetrics2 (); //clientes que más veces compraron

    @Query(value = "select  count(b.total) from clients c inner join bills b on b.cliente_id = c.id where c.id =?1",nativeQuery = true)
    Double getNumberOfBills(Long id);

    @Query(value = "select sum(b.total),count(*),month(b.created_At),year(b.created_At) from clients c inner join bills b on b.cliente_id = c.id where b.created_at between '2021-08-01'and'2022-01-31'group by month(b.created_At) order by (b.created_At)",nativeQuery = true)
    List<List<Double>> getMetricsByDate();


}
