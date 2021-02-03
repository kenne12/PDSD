/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Axe;
import entities.District;
import entities.Indicateur;
import entities.IndicateurDistrict;
import entities.Probleme;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ProblemeFacadeLocal {

    void create(Probleme probleme);

    void edit(Probleme probleme);

    void remove(Probleme probleme);

    Probleme find(Object id);

    List<Probleme> findAll();

    List<Probleme> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Probleme> find(Indicateur indicateur, District district, Sousaxe sousaxe) throws Exception;

    List<Probleme> find(District district, Sousaxe sousaxe, int observation) throws Exception;

    List<Probleme> find(District district, Sousaxe sousaxe) throws Exception;

    List<Probleme> find(IndicateurDistrict indicateurDistrict) throws Exception;

    List<Probleme> find(Indicateur indicateur, District district, int observation) throws Exception;

    List<Probleme> findByAxeDistrict(Axe axe, District district, int observation) throws Exception;

    List<Probleme> findBySousAxeDistrict(Sousaxe sousaxe, District district, int observation) throws Exception;

}
