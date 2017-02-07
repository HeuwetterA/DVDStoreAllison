
package DVDRental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author allis
 */
public class FilmActorHelper {
    
    Session session = null;
    
    public FilmActorHelper(){
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getActors(){
        
        List<Actor> actorList = null;
        
        String sql = "select * from actor";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Actor.class);
            
            actorList = (List<Actor>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return actorList;
    }
    
    public List getCategories(){
        
        List<Category> CategoryList = null;
        
        String sql = "select * from category";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Category.class);
            
            CategoryList = (List<Category>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return CategoryList;
    }
    
    public List getLanguages(){
        
        // setting up local variable that will be used to return
        //a list of languages
        List<Language> languageList = null;
        
        //Creating our query, as a string
        String sql = "select * from language";
        
          
        try {
            // if the current transaction isn't active, begin one
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            // Creating the query to be executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            // associating the Language POJO and table with the query
            q.addEntity(Language.class);
            
            // executing the query
            languageList = (List<Language>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return languageList;
    }
}
