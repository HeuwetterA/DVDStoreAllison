
package DVDRental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author allis
 */
@Named(value = "actorController")
@SessionScoped
public class ActorController implements Serializable {

    //map directly to components in the actor.xhtml
    String firstName;
    String lastName;
    String response;
    
    //classs that uses Hibernate to query the actor table
    ActorHelper helper;
    
    //Actor POJO
    Actor actor;
    
    /**
     * Creates a new instance of ActorController
     */
    public ActorController() {
        helper = new ActorHelper();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResponse() {
        
        if (firstName != null && lastName != null){
            
            //current data in SQL format
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
                    
            //initializing an actor
            actor = new Actor(firstName, lastName, timeStamp);
            
            //calling helper method that inserts a row into the actor table
            if (helper.insertActor(actor) == 1){
                //insert was successful
                firstName = null;
                lastName = null;
                response = "Actor Added.";
                return response;
            } else{
                //insert failed
                firstName = null;
                lastName = null;
                response = "Actor Not Added.";
                return response;
            }
            
        }else {
            //doesn't display a message when the user hasn't input
            //a first and last name
            response = " ";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
    
}
