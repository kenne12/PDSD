/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periode;
import entities.Periodicite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PeriodeFacadeLocal {

    void create(Periode periode);

    void edit(Periode periode);

    void remove(Periode periode);

    Periode find(Object id);

    List<Periode> findAll();

    List<Periode> findRange(int[] range);

    int count();

    int nextId();

    List<Periode> findByNom(String nom);

    List<Periode> findByPeriodicite(int periodicite, Boolean etat);

    List<Periode> findByPeriodicite(Periodicite periodicite) throws Exception;

}
