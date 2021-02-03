/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.District;
import entities.Probleme;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActiviteFacadeLocal {

    void create(Activite activite);

    void edit(Activite activite);

    void remove(Activite activite);

    Activite find(Object id);

    List<Activite> findAll();

    List<Activite> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Activite> findAllRange();

    List<Activite> findByProbleme(Probleme probleme) throws Exception;

    List<Activite> findBySousAxe(Sousaxe sousaxe, District district) throws Exception;

    List<Activite> find(District district, Sousaxe sousaxe, int observation) throws Exception;

    List<Activite> findByDistrict(District district) throws Exception;

}
