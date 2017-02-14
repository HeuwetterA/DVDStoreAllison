
package DVDRental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author allis
 */
public class FilmHelper {
    
    Session session = null;

    public FilmHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getFilmTitles (int startID){
        List<Film> filmList = null;
        
        String sql = "select * from film order by title limit :start, :end";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);
            
            q.setParameter("start", startID);
            q.setParameter("end", 10);
            
            filmList = (List<Film>) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return filmList;
    }
    
    public int getNumberFilms(){
        
        List<Film> filmList = null;
        
        String sql = "select * from film";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);
            
            filmList = (List<Film>) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return filmList.size();
    }
}
