package ar.com.frigeriofranco.practic.repository;

import ar.com.frigeriofranco.practic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
