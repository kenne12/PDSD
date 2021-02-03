/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieintervention;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CategorieinterventionFacadeLocal {

    void create(Categorieintervention categorieintervention);

    void edit(Categorieintervention categorieintervention);

    void remove(Categorieintervention categorieintervention);

    Categorieintervention find(Object id);

    List<Categorieintervention> findAll();

    List<Categorieintervention> findRange(int[] range);

    int count();
    
    List<Categorieintervention> findAllRange();

    Integer nextId() throws Exception;

    List<Categorieintervention> find(Sousaxe sousaxe) throws Exception;

}
