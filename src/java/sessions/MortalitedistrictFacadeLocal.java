/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Mortalite;
import entities.Mortalitedistrict;
import entities.Rubriquemortalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MortalitedistrictFacadeLocal {

    void create(Mortalitedistrict mortalitedistrict);

    void edit(Mortalitedistrict mortalitedistrict);

    void remove(Mortalitedistrict mortalitedistrict);

    Mortalitedistrict find(Object id);

    List<Mortalitedistrict> findAll();

    List<Mortalitedistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    public List<Mortalitedistrict> findByDistrict(int district);

    public List<Mortalitedistrict> find(Mortalite mortalite, Rubriquemortalite rubriquemortalite) throws Exception;

    public List<Mortalitedistrict> find(Mortalite mortalite) throws Exception;

    List<Mortalitedistrict> find(Mortalite mortalite, Rubriquemortalite rubriquemortalite, District district) throws Exception;
}
