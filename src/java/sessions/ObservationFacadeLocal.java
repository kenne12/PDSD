/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Observation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ObservationFacadeLocal {

    void create(Observation observation);

    void edit(Observation observation);

    void remove(Observation observation);

    Observation find(Object id);

    List<Observation> findAll();

    List<Observation> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Observation> findByNom(String nom);
    
}
