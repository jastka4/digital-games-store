package org.jastka4.digitalgamesstorebackoffice.service;

import org.jastka4.digitalgamesstorebackoffice.data.ProductData;
import org.jastka4.digitalgamesstorebackoffice.forms.ProductForm;
import org.jastka4.digitalgamesstorebackoffice.model.Product;
import org.jastka4.digitalgamesstorebackoffice.rabbitmq.RabbitMQSender;
import org.jastka4.digitalgamesstorebackoffice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class ProductService {

    @Resource
    private RabbitMQSender rabbitMQSender;

    @Resource
    private ProductRepository productRepository;

    public Page<ProductData> getPaginated(final Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductData::fromEntity);
    }

    public ProductData getByCodeAndOnlineCatalogue(final boolean onlineCatalogue, final String code) {
        if (code == null || code.length() <= 0) {
            return null;
        }
        return ProductData.fromEntity(productRepository.findByCodeAndOnlineCatalogue(code, onlineCatalogue));
    }

    public void updateOrCreate(final ProductForm productForm) throws IOException {
        Product product = productRepository.findByCodeAndOnlineCatalogue(productForm.getCode(), false);
        if (!ObjectUtils.isEmpty(product)) {
            product.setCode(productForm.getCode());
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            product.setImage(productForm.getFileData().getBytes());
        } else {
            product = new Product(productForm);
        }

        productRepository.save(product);
    }

    public void sync(final String code) {
        ProductData product = ProductData.fromEntity(productRepository.findByCodeAndOnlineCatalogue(code, false));

        rabbitMQSender.send(product);
    }
}
