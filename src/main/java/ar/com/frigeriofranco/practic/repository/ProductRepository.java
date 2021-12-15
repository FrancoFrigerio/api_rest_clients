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
}
