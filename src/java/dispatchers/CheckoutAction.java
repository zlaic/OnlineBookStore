/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;
import javax.servlet.http.*;
/**
 *
 * @author Dea
 */
public class CheckoutAction implements Action {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/checkout.jsp";
    }
}