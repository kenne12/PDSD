/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typepathologie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypepathologieFacadeLocal {

    void create(Typepathologie typepathologie);

    void edit(Typepathologie typepathologie);

    void remove(Typepathologie typepathologie);

    Typepathologie find(Object id);

    List<Typepathologie> findAll();

    List<Typepathologie> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Typepathologie> findByNom(String nom);
}
