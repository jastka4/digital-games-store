package org.jastka4.digitalgamesstore.service;

import org.jastka4.digitalgamesstore.data.ProductData;
import org.jastka4.digitalgamesstore.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;

    public Page<ProductData> getPaginated(final Pageable pageable) {
        return productRepository.findAllByOnlineCatalogue(true, pageable).map(ProductData::fromEntity);
    }

    public ProductData getByCode(final String code) {
        if (code == null || code.length() <= 0) {
            return null;
        }
        return ProductData.fromEntity(productRepository.findByCode(code));
    }
}
