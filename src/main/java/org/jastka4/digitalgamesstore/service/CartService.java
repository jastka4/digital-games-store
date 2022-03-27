package org.jastka4.digitalgamesstore.service;

import org.jastka4.digitalgamesstore.data.CartData;
import org.jastka4.digitalgamesstore.data.ProductData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartData.CartLineData findLineByCode(final CartData cart, String code) {
        for (CartData.CartLineData line : cart.getCartLines()) {
            if (line.getProductData().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(final CartData cart, final ProductData productData, final int quantity) {
        CartData.CartLineData line = findLineByCode(cart, productData.getCode());

        if (line == null) {
            line = new CartData.CartLineData();
            line.setQuantity(0);
            line.setProductData(productData);
            cart.getCartLines().add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            cart.getCartLines().remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void updateProduct(final CartData cart, final String code, final int quantity) {
        CartData.CartLineData line = this.findLineByCode(cart, code);

        if (line != null) {
            if (quantity <= 0) {
                cart.getCartLines().remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(final CartData cart, final ProductData productInfo) {
        CartData.CartLineData line = this.findLineByCode(cart, productInfo.getCode());
        if (line != null) {
            cart.getCartLines().remove(line);
        }
    }

    public boolean isEmpty(final CartData cart) {
        return cart.getCartLines().isEmpty();
    }

    public boolean isValidCustomer(final CartData cart) {
        return cart.getCustomerData() != null;
    }

    public int getQuantityTotal(final CartData cart) {
        int quantity = 0;
        for (CartData.CartLineData line : cart.getCartLines()) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal(final CartData cart) {
        double total = 0;
        for (CartData.CartLineData line : cart.getCartLines()) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(final CartData cart, CartData cartForm) {
        if (cartForm != null) {
            List<CartData.CartLineData> lines = cartForm.getCartLines();
            for (CartData.CartLineData line : lines) {
                updateProduct(cart, line.getProductData().getCode(), line.getQuantity());
            }
        }
    }
}