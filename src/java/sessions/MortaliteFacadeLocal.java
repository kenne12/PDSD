/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mortalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MortaliteFacadeLocal {

    void create(Mortalite mortalite);

    void edit(Mortalite mortalite);

    void remove(Mortalite mortalite);

    Mortalite find(Object id);

    List<Mortalite> findAll();

    List<Mortalite> findRange(int[] range);

    int count();

    int nextId();

    public List<Mortalite> findByNom(String nom);

    public List<Mortalite> findByDistrict(int district);

    List<Mortalite> findAllRange();
    
    public List<Mortalite> findAllRangeCode() ;

    List<Mortalite> findAllRangeId();
}
