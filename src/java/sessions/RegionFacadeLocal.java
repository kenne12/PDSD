/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Region;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RegionFacadeLocal {

    void create(Region region);

    void edit(Region region);

    void remove(Region region);

    Region find(Object id);

    List<Region> findAll();

    List<Region> findRange(int[] range);

    int count();

    int nextId();

    List<Region> findByNom(String nom);

    List<Region> find(Boolean etat) throws Exception;

    List<Region> findAllRange();

}
