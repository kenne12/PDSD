/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypestructureFacadeLocal {

    void create(Typestructure typestructure);

    void edit(Typestructure typestructure);

    void remove(Typestructure typestructure);

    Typestructure find(Object id);

    List<Typestructure> findAll();

    List<Typestructure> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typestructure> findByNom(String nom);
}
