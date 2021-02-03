/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Indicateur;
import entities.ResultatAttenduDistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ResultatAttenduDistrictFacadeLocal {

    void create(ResultatAttenduDistrict resultatAttenduDistrict);

    void edit(ResultatAttenduDistrict resultatAttenduDistrict);

    void remove(ResultatAttenduDistrict resultatAttenduDistrict);

    ResultatAttenduDistrict find(Object id);

    List<ResultatAttenduDistrict> findAll();

    List<ResultatAttenduDistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ResultatAttenduDistrict> findByDistrict(District district) throws Exception;

    List<ResultatAttenduDistrict> findByIndicateur(Indicateur indicateteur) throws Exception;

    List<ResultatAttenduDistrict> findByIndicateur(Indicateur indicateteur, District district) throws Exception;

}
