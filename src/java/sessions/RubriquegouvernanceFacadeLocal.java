/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquegouvernance;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface RubriquegouvernanceFacadeLocal {

    void create(Rubriquegouvernance rubriquegouvernance);

    void edit(Rubriquegouvernance rubriquegouvernance);

    void remove(Rubriquegouvernance rubriquegouvernance);

    Rubriquegouvernance find(Object id);

    List<Rubriquegouvernance> findAll();

    List<Rubriquegouvernance> findRange(int[] range);

    int count();

    public int nextId();

    public List<Rubriquegouvernance> findByNom(String nom);

    public List<Rubriquegouvernance> findAllRange();

}
