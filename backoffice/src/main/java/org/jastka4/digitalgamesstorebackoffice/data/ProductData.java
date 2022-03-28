package org.jastka4.digitalgamesstorebackoffice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jastka4.digitalgamesstorebackoffice.model.Product;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductData implements Serializable {
    @Serial
    private static final long serialVersionUID = -1000119078147252957L;

    private String code;
    private String name;
    private double price;
    private boolean onlineCatalogue;

    public static ProductData fromEntity(final Product product) {
        return new ProductData(product.getCode(), product.getName(), product.getPrice(), product.isOnlineCatalogue());
    }
}
