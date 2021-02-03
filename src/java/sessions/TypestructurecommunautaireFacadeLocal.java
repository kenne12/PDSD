/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typestructurecommunautaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypestructurecommunautaireFacadeLocal {

    void create(Typestructurecommunautaire typestructurecommunautaire);

    void edit(Typestructurecommunautaire typestructurecommunautaire);

    void remove(Typestructurecommunautaire typestructurecommunautaire);

    Typestructurecommunautaire find(Object id);

    List<Typestructurecommunautaire> findAll();

    List<Typestructurecommunautaire> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typestructurecommunautaire> findByNom(String nom);
    
    List<Typestructurecommunautaire> findAllRange();
    
}
