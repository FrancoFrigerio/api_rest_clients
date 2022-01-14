package ar.com.frigeriofranco.practic.repository;

import ar.com.frigeriofranco.practic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * from products where name like %?1%",nativeQuery = true)
    List<Product> getProductLike(String name);

    @Query(value = "select  p.id, p.name,sum(i.count)from items i inner join products p on i.product_id = p.id inner join bills b on i.bill_id = b.id where b.created_at between '2021-08-01'and'2022-01-31' group by p.name" ,nativeQuery = true)
    List<List<String>> getMetrics();
}
