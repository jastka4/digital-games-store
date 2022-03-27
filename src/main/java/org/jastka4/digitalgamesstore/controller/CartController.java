package org.jastka4.digitalgamesstore.controller;

import org.jastka4.digitalgamesstore.data.CartData;
import org.jastka4.digitalgamesstore.data.ProductData;
import org.jastka4.digitalgamesstore.service.CartService;
import org.jastka4.digitalgamesstore.service.ProductService;
import org.jastka4.digitalgamesstore.utils.CartUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartData cartData;

    @Resource
    private ProductService productService;

    @Resource
    private CartService cartService;

    @GetMapping
    public String shoppingCartHandler(final Model model, final HttpSession session) {
        final CartData cart = CartUtils.getCartInSession(session);

        model.addAttribute("cartForm", cartData);
        return "cart/index";
    }

    @PostMapping("/add")
    public String add(final HttpSession session,
                      @RequestParam(value = "code", defaultValue = "") final String code) {
        final CartData cart = CartUtils.getCartInSession(session);

        final ProductData product = productService.getByCode(code);
        if (product != null) {
            cartService.addProduct(cartData, product, 1);
        }

        CartUtils.setCartInSession(session, cart);

        return "redirect:/cart";
    }
}