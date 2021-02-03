/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Profilpersonnel;
import entities.Rhs;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RhsFacadeLocal {

    void create(Rhs rhs);

    void edit(Rhs rhs);

    void remove(Rhs rhs);

    Rhs find(Object id);

    List<Rhs> findAll();

    List<Rhs> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Rhs> find(Structure structure, Profilpersonnel profilpersonnel, Annee annee) throws Exception;

    List<Rhs> find(Structure structure, Annee annee) throws Exception;
    
    List<Rhs> find(Structure structure) throws Exception;
    

}
