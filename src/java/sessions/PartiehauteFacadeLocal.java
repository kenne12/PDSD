/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Partiehaute;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface PartiehauteFacadeLocal {

    void create(Partiehaute partiehaute);

    void edit(Partiehaute partiehaute);

    void remove(Partiehaute partiehaute);

    Partiehaute find(Object id);

    List<Partiehaute> findAll();

    List<Partiehaute> findRange(int[] range);

    int count();

    int nextId();

    public List<Partiehaute> findByIntroduction(String introduction);

    public List<Partiehaute> findByDistrict(int district);
    
    public List<Partiehaute> findByDistrict(District district) throws Exception ;
}
