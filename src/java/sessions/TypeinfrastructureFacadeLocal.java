/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typeinfrastructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeinfrastructureFacadeLocal {

    void create(Typeinfrastructure typeinfrastructure);

    void edit(Typeinfrastructure typeinfrastructure);

    void remove(Typeinfrastructure typeinfrastructure);

    Typeinfrastructure find(Object id);

    List<Typeinfrastructure> findAll();

    List<Typeinfrastructure> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typeinfrastructure> findByNom(String nom);
    
}
