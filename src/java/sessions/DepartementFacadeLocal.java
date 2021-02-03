/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Departement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface DepartementFacadeLocal {

    void create(Departement departement);

    void edit(Departement departement);

    void remove(Departement departement);

    Departement find(Object id);

    List<Departement> findAll();

    List<Departement> findRange(int[] range);

    int count();
    
    int nextId();
    
    List<Departement>findByNom(String nom);
    
}
