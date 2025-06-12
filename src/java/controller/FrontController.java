package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;
import dispatchers.*;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {

    private final HashMap actions = new HashMap();
    

    /**
     * Initialize global variables.
     * @param config ServletConfig object
     * @throws ServletException if an error occurs during initialization
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Additional initialization code can be added here
        actions.put("add_to_cart", new dispatchers.AddToCartAction());
        actions.put("view_cart", new dispatchers.ViewCartAction());
        actions.put("checkout", new dispatchers.CheckoutAction());
        actions.put("continue", new dispatchers.ContinueAction());
        actions.put("update_cart", new dispatchers.UpdateCartAction());
        actions.put("default", new dispatchers.DefaultAction());
    }

    /**
     * Process the HTTP GET request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        // Forward GET requests to doPost method
        doPost(request, response);
    }

    /**
     * Process the HTTP POST request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String requestedAction = request.getParameter("action");
        if (requestedAction == null || !actions.containsKey(requestedAction)) {
            requestedAction = "default"; 
        }

        Action action = (Action)actions.get(requestedAction);
        String nextPage = action.execute(request, response);

        dispatch(request, response, nextPage);
    }


    /**
     * Forward the request to the specified page.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page Page to forward to
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Get Servlet information.
     * @return Servlet information
     */
    public String getServletInfo() {
        return "controller.FrontController Information";
    }
}

