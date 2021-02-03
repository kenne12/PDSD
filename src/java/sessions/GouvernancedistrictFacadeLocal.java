/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Gouvernancedistrict;
import entities.Rubriquegouvernance;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface GouvernancedistrictFacadeLocal {

    void create(Gouvernancedistrict gouvernancedistrict);

    void edit(Gouvernancedistrict gouvernancedistrict);

    void remove(Gouvernancedistrict gouvernancedistrict);

    Gouvernancedistrict find(Object id);

    List<Gouvernancedistrict> findAll();

    List<Gouvernancedistrict> findRange(int[] range);

    int count();

    public Integer nextId() throws Exception;

    public List<Gouvernancedistrict> find(Structure structure, Rubriquegouvernance rubriquegouvernance, District district) throws Exception;

    public List<Gouvernancedistrict> find(District district) throws Exception;

    public List<Gouvernancedistrict> find(Structure structure) throws Exception;

}
