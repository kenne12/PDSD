/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquemorbidite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RubriquemorbiditeFacadeLocal {

    void create(Rubriquemorbidite rubriquemorbidite);

    void edit(Rubriquemorbidite rubriquemorbidite);

    void remove(Rubriquemorbidite rubriquemorbidite);

    Rubriquemorbidite find(Object id);

    List<Rubriquemorbidite> findAll();

    List<Rubriquemorbidite> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Rubriquemorbidite> findByNom(String nom) ;
    
    public List<Rubriquemorbidite> findAllRange();
}
