package org.jastka4.digitalgamesstore.repository;

import org.jastka4.digitalgamesstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);

    Page<Product> findAllByOnlineCatalogue(final boolean onlineCatalogue, final Pageable pageable);
}
