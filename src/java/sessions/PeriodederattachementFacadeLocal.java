/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periodederattachement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PeriodederattachementFacadeLocal {

    void create(Periodederattachement periodederattachement);

    void edit(Periodederattachement periodederattachement);

    void remove(Periodederattachement periodederattachement);

    Periodederattachement find(Object id);

    List<Periodederattachement> findAll();

    List<Periodederattachement> findRange(int[] range);

    int count();
    
    int nextId();
    
    List<Periodederattachement> findByNom(String nom);
    
    List<Periodederattachement> findByEtat(boolean etat);
    
}
