package utility;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Book;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dea
 */
@Stateless
public class BookDAO {

    @PersistenceContext(unitName = "BookShopPU")
    private EntityManager em;

    /**
     *
     * @return
     */
    public List<Book> getAllBooks() {
        List<Book> books = null;
        try {
            books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error loading books", e);
        }
        return books;
    }

    /**
     *
     * @param book
     */
    public void persist(Book book) {
        try {
            em.persist(book);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error persisting book", e);
            throw new RuntimeException(e);
        }
    }
}
