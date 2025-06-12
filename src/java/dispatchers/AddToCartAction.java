/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import model.Book;
import model.CartItem;
import javax.servlet.http.*;
import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author Dea
 */
public class AddToCartAction implements Action {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");
        String nextPage = "/jsp/titles.jsp";

        if (selectedBooks == null || selectedBooks.length == 0) {
            return nextPage;
        }

        if (cart == null) {
            cart = new HashMap<String, CartItem>();
        }

        for (int i = 0; i < selectedBooks.length; i++) {
            String isbn = selectedBooks[i];
            int quantity = Integer.parseInt(request.getParameter(isbn));
            if (cart.containsKey(isbn)) {
                CartItem item = (CartItem) cart.get(isbn);
                item.setQuantity(quantity);
            } else {
                Book book = getBookFromSession(isbn, session);
                CartItem item = new CartItem(book);
                item.setQuantity(quantity);
                cart.put(isbn, item);
            }
        }

        session.setAttribute("cart", cart);
        return nextPage;
    }

    private Book getBookFromSession(String isbn, HttpSession session) {
        java.util.List<Book> books = (java.util.List<Book>) session.getAttribute("books");
        if (books == null) return null;
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }
}