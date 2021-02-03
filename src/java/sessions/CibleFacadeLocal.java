/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Cible;
import entities.District;
import entities.IndicateurDistrict;
import entities.Probleme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CibleFacadeLocal {

    void create(Cible cible);

    void edit(Cible cible);

    void remove(Cible cible);

    Cible find(Object id);

    List<Cible> findAll();

    List<Cible> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Cible> findByProbleme(Probleme probleme) throws Exception;

    List<Cible> findByProbleme(Probleme probleme, Annee annee) throws Exception;

    List<Cible> find(IndicateurDistrict indicateurDistrict, Annee annee) throws Exception;

    List<Cible> find(IndicateurDistrict indicateurDistrict) throws Exception;

    List<Cible> find(District district) throws Exception;

}
