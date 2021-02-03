/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeacteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeacteurFacadeLocal {

    void create(Typeacteur typeacteur);

    void edit(Typeacteur typeacteur);

    void remove(Typeacteur typeacteur);

    Typeacteur find(Object id);

    List<Typeacteur> findAll();

    List<Typeacteur> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typeacteur> findByNom(String nom) ;
}
