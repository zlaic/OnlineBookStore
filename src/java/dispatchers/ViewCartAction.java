/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;
import model.CartItem;
import javax.servlet.http.*;
import java.util.Map;

/**
 *
 * @author Dea
 */
public class ViewCartAction implements Action {

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

        if (cart == null) {
            return "/jsp/titles.jsp";
        } else {
            return "/jsp/cart.jsp";
        }
    }
}
