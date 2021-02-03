/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.Annee;
import entities.Chronogramme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ChronogrammeFacadeLocal {

    void create(Chronogramme chronogramme);

    void edit(Chronogramme chronogramme);

    void remove(Chronogramme chronogramme);

    Chronogramme find(Object id);

    List<Chronogramme> findAll();

    List<Chronogramme> findRange(int[] range);

    int count();

    Long nextId() throws Exception;
    
    List<Chronogramme>findByActivite(Activite activite)throws Exception;
    
    List<Chronogramme>findByActivite(Activite activite , Annee annee)throws Exception;

}
