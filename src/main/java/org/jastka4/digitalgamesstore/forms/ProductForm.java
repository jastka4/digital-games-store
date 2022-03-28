package org.jastka4.digitalgamesstore.forms;

import lombok.Data;
import org.jastka4.digitalgamesstore.data.ProductData;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductForm {
    private String code;
    private String name;
    private double price;
    private boolean newProduct;

    private MultipartFile fileData;

    public ProductForm() {
        this.newProduct = true;
    }

    public ProductForm(ProductData product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}