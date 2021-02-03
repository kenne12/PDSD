/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typefacteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypefacteurFacadeLocal {

    void create(Typefacteur typefacteur);

    void edit(Typefacteur typefacteur);

    void remove(Typefacteur typefacteur);

    Typefacteur find(Object id);

    List<Typefacteur> findAll();

    List<Typefacteur> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typefacteur> findByNom(String nom);
    
}
