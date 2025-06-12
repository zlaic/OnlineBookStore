/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import model.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import utility.BookDAO;
import javax.servlet.http.*;
import java.util.List;

/**
 *
 * @author Dea
 */
public class DefaultAction implements Action {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String nextPage = "/jsp/error.jsp";

        try {
            // Use JNDI to look up the EJB
            Context ctx = new InitialContext();
            BookDAO dao = (BookDAO) ctx.lookup("java:module/BookDAO");

            List<Book> books = dao.getAllBooks();
            System.out.println("Books loaded: " + books.size());
            session.setAttribute("books", books);
            nextPage = "/jsp/titles.jsp";

        } catch (Exception ex) {
            ex.printStackTrace(); // log full error
            request.setAttribute("result", "Error loading books: " + ex);
        }
        return nextPage;
    }
}