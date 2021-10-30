package com.example.MidtermDb.Product;

import com.example.MidtermDb.Product.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>  {
    Optional<Products> findByIdAndCustomersId(Long id, Long productsId);
    List<Products> findByCustomersId(Long id);


}
