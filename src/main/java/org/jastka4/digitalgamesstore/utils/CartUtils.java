package org.jastka4.digitalgamesstore.utils;

import org.jastka4.digitalgamesstore.data.CartData;

import javax.servlet.http.HttpSession;

public class CartUtils {
    public static CartData getCartInSession(final HttpSession httpSession) {

        CartData cartData = (CartData) httpSession.getAttribute("sessionCart");
        if (cartData == null) {
            httpSession.setAttribute("sessionCart", new CartData());
        }

        return cartData;
    }

    public static void setCartInSession(final HttpSession httpSession, final CartData cartData) {
        httpSession.setAttribute("sessionCart", cartData);
    }

    public static void removeCartInSession(final HttpSession httpSession) {
        httpSession.removeAttribute("sessionCart");
    }

    public static void storeLastOrderedCartInSession(final HttpSession httpSession, final CartData cartData) {
        httpSession.setAttribute("lastOrderedCart", cartData);
    }

    public static CartData getLastOrderedCartInSession(final HttpSession httpSession) {
        return (CartData) httpSession.getAttribute("lastOrderedCart");
    }
}
