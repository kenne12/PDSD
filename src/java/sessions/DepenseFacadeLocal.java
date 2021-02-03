/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Depense;
import entities.Naturedepense;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface DepenseFacadeLocal {

    void create(Depense depense);

    void edit(Depense depense);

    void remove(Depense depense);

    Depense find(Object id);

    List<Depense> findAll();

    List<Depense> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Depense> find(Structure structure, Naturedepense naturedepense, Annee annee) throws Exception;

    List<Depense> find(Structure structure, Annee annee) throws Exception;
    
    List<Depense> find(Structure structure) throws Exception;

}
