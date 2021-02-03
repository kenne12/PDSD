/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Arrondissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ArrondissementFacadeLocal {

    void create(Arrondissement arrondissement);

    void edit(Arrondissement arrondissement);

    void remove(Arrondissement arrondissement);

    Arrondissement find(Object id);

    List<Arrondissement> findAll();

    List<Arrondissement> findRange(int[] range);

    int count();
    
    int nextId();
    
    List<Arrondissement> findByNom(String nom);
    
}
