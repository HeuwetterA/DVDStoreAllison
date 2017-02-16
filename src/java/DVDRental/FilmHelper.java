
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
    
    public List getActorsByID (int filmId){
        
        List<Actor> actorList = null;
        
        String sql = "select * from actor, film_actor, film "
                + "where actor.actor_id = film_actor.actor_id "
                + "and film_actor.film_id = film.film_id "
                + "and film.film_id = :id";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Actor.class);
            
            q.setParameter("id", filmId);
            
            actorList = (List<Actor>) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return actorList;
    }
    
    public Category getCategoryByID (int filmId){
        
        List<Category> categoryList = null;
        
        String sql = "select * from category, film_category, film "
                + "where category.category_id = film_category.category_id "
                + "and film_category.film_id = film.film_id "
                + "and film.film_id = :id";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Category.class);
            
            q.setParameter("id", filmId);
            
            categoryList = (List<Category>) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return categoryList.get(0);
    }
    
    public Film getFilmDetails (int filmId){
        
        Film film = null;
        
        String sql = "select * from film where film_id = :id";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);
            
            q.setParameter("id", filmId);
            
            film = (Film) q.uniqueResult();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return film;
    }
    
    public String getLanguageByID (int langId) {
        
        Language language = null;
        
        String sql = "select * from language where language_id = :id";
        
        try {
            
            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Language.class);
            
            q.setParameter("id", langId);
            
            language = (Language) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return language.getName();
    }
}
