/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Recette;
import entities.Sourcefinancement;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RecetteFacadeLocal {

    void create(Recette recette);

    void edit(Recette recette);

    void remove(Recette recette);

    Recette find(Object id);

    List<Recette> findAll();

    List<Recette> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Recette> find(Structure structure, Sourcefinancement sourcefinancement, Annee annee) throws Exception;

    List<Recette> find(Structure structure, Annee annee) throws Exception;
    
    List<Recette> find(Structure structure) throws Exception;

}
