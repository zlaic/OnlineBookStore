/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;


/**
 *
 * @author Dea
 */
public interface Action {

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
