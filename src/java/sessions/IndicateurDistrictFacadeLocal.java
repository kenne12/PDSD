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
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface IndicateurDistrictFacadeLocal {

    void create(IndicateurDistrict indicateurDistrict);

    void edit(IndicateurDistrict indicateurDistrict);

    void remove(IndicateurDistrict indicateurDistrict);

    IndicateurDistrict find(Object id);

    List<IndicateurDistrict> findAll();

    List<IndicateurDistrict> findRange(int[] range);

    int count();

    Long nextVal();

    List<IndicateurDistrict> findByIndicateur(Indicateur indicateur) throws Exception;

    List<IndicateurDistrict> findByIndicateur(Indicateur indicateur, District district) throws Exception;

    List<IndicateurDistrict> findByDistrictSousaxe(District district, Sousaxe sousaxe) throws Exception;

    List<IndicateurDistrict> findByDistrictAxeObservation(District district, Axe axe, int idobservation) throws Exception;

    List<IndicateurDistrict> findByDistrict(District district) throws Exception;

    List<IndicateurDistrict> findByDistrictSousaxeObservation(District district, Sousaxe sousaxe, int idobservation) throws Exception;

    List<IndicateurDistrict> findByDistrictIndicateurObservation(District district, Indicateur indicateur, int idobservation) throws Exception;

}
