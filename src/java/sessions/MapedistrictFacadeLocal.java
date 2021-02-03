/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.District;
import entities.Mape;
import entities.Mapedistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MapedistrictFacadeLocal {

    void create(Mapedistrict mapedistrict);

    void edit(Mapedistrict mapedistrict);

    void remove(Mapedistrict mapedistrict);

    Mapedistrict find(Object id);

    List<Mapedistrict> findAll();

    List<Mapedistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    public List<Mapedistrict> find(Mape mape) throws Exception;

    public List<Mapedistrict> find(Mape mape, Annee annee) throws Exception;

    public List<Mapedistrict> findByDistrict(int district);

    List<Mapedistrict> find(Mape mape, Annee annee, District district) throws Exception;

}
