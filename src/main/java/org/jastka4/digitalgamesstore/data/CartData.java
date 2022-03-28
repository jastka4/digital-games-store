package org.jastka4.digitalgamesstore.data;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class CartData implements Serializable {
    @Serial
    private static final long serialVersionUID = -2576670215015463100L;

    private final List<CartLineData> cartLines = new ArrayList<>();
    private int orderNum;
    private CustomerData customerData;

    @Data
    public static class CartLineData implements Serializable {
        @Serial
        private static final long serialVersionUID = 7550745928843183535L;

        private ProductData productData;
        private int quantity;

        public double getAmount() {
            return this.productData.getPrice() * this.quantity;
        }
    }
}
