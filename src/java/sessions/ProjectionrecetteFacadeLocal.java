/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Projectionrecette;
import entities.Sourcefinancement;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Local
public interface ProjectionrecetteFacadeLocal {

    void create(Projectionrecette projectionrecette);

    void edit(Projectionrecette projectionrecette);

    void remove(Projectionrecette projectionrecette);

    Projectionrecette find(Object id);

    List<Projectionrecette> findAll();

    List<Projectionrecette> findRange(int[] range);

    int count();
    
    Integer nextId() throws Exception;

    List<Projectionrecette> find(Structure structure, Sourcefinancement sourcefinancement, Annee annee) throws Exception;

    List<Projectionrecette> find(Structure structure, Annee annee) throws Exception;
    
    List<Projectionrecette> find(Structure structure) throws Exception;

}
