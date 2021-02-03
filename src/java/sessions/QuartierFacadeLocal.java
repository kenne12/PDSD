/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Quartier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface QuartierFacadeLocal {

    void create(Quartier quartier);

    void edit(Quartier quartier);

    void remove(Quartier quartier);

    Quartier find(Object id);

    List<Quartier> findAll();

    List<Quartier> findRange(int[] range);

    int count();
    
    int nextId();
    
    List<Quartier> findByLibelle(String libelle);
    
}
