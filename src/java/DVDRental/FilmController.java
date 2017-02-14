
package DVDRental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author allis
 */
@Named(value = "filmController")
@SessionScoped
public class FilmController implements Serializable {

    int startId;
    
    DataModel filmTitles;
    
    FilmHelper helper;
    
    private int recordCount;
    private int pageSize = 10;
    
    /**
     * Creates a new instance of FilmController
     */
    public FilmController() {
        
        helper = new FilmHelper();
        startId = 0;
        recordCount = helper.getNumberFilms();
    }

    public DataModel getFilmTitles() {
        
        if (filmTitles == null){
            filmTitles = new ListDataModel(helper.getFilmTitles(startId));
        }
        return filmTitles;
    }

    public void setFilmTitles(DataModel filmTitles) {
        this.filmTitles = filmTitles;
    }
    
    // this method sets filmTitles to null
    // if this field is null when the index.xhtml page reloads, then
    // more films will be retrieved
    private void recreateModel(){
        filmTitles = null;
        recordCount = helper.getNumberFilms();
    }
    
    // this method get called when the next hyperlink gets clicked
    // it increments the startIdby the pageSize and forces
    // the page to get more films when it reloads
    public String next(){
        startId = startId + (pageSize + 1);
        recreateModel();
        return "index";
    }
    
    // this method gets called when the previous hyperlink gets clicked
    // it decrements the startId by the pageSize and forces
    // the page to get more films when it reloads
    public String previous(){
        startId = startId - (pageSize + 1);
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
}
