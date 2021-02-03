/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Statutstructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface StatutstructureFacadeLocal {

    void create(Statutstructure statutstructure);

    void edit(Statutstructure statutstructure);

    void remove(Statutstructure statutstructure);

    Statutstructure find(Object id);

    List<Statutstructure> findAll();

    List<Statutstructure> findRange(int[] range);

    int count();

    int nextId();

    public List<Statutstructure> findByNom(String nom);

    List<Statutstructure> findAllRange();
}
