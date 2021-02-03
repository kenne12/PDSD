/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatequipement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface EtatequipementFacadeLocal {

    void create(Etatequipement etatequipement);

    void edit(Etatequipement etatequipement);

    void remove(Etatequipement etatequipement);

    Etatequipement find(Object id);

    List<Etatequipement> findAll();

    List<Etatequipement> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

}
