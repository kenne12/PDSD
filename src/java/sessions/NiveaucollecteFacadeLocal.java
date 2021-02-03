/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Niveaucollecte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface NiveaucollecteFacadeLocal {

    void create(Niveaucollecte niveaucollecte);

    void edit(Niveaucollecte niveaucollecte);

    void remove(Niveaucollecte niveaucollecte);

    Niveaucollecte find(Object id);

    List<Niveaucollecte> findAll();

    List<Niveaucollecte> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;
}
