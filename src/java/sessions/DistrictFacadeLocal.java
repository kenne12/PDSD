/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface DistrictFacadeLocal {

    void create(District district);

    void edit(District district);

    void remove(District district);

    District find(Object id);

    List<District> findAll();

    List<District> findRange(int[] range);

    int count();

    int nextId();

    List<District> findByNom(String nom);

    List<District> findByRegion(int region);

    List<District> findAllRange();

}
