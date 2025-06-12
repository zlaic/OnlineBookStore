/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;
import model.CartItem;
import javax.servlet.http.*;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
/**
 *
 * @author Dea
 */
public class UpdateCartAction implements Action {

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
        String nextPage = "/jsp/cart.jsp";

        String[] booksToRemove = request.getParameterValues("remove");
        if (booksToRemove != null && cart != null) {
            for (String bookToRemove : booksToRemove) {
                cart.remove(bookToRemove);
            }
        }

        if (cart != null) {
            Set<Map.Entry<String, CartItem>> entries = cart.entrySet();
            Iterator<Map.Entry<String, CartItem>> iter = entries.iterator();
            while (iter.hasNext()) {
                Map.Entry<String, CartItem> entry = iter.next();
                String isbn = entry.getKey();
                CartItem item = entry.getValue();
                int quantity = Integer.parseInt(request.getParameter(isbn));
                item.updateQuantity(quantity);
            }
        }

        return nextPage;
    }
}