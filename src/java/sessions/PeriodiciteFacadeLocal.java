/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periodicite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PeriodiciteFacadeLocal {

    void create(Periodicite periodicite);

    void edit(Periodicite periodicite);

    void remove(Periodicite periodicite);

    Periodicite find(Object id);

    List<Periodicite> findAll();

    List<Periodicite> findRange(int[] range);

    int count();

    int nextId();

    List<Periodicite> findByNom(String nom);

    List<Periodicite> findAllRange();

}
