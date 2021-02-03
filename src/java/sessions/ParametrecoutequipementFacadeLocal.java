/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etatequipement;
import entities.Parametrecoutequipement;
import entities.Typeequipementtraceur;
import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ParametrecoutequipementFacadeLocal {

    void create(Parametrecoutequipement parametrecoutequipement);

    void edit(Parametrecoutequipement parametrecoutequipement);

    void remove(Parametrecoutequipement parametrecoutequipement);

    Parametrecoutequipement find(Object id);

    List<Parametrecoutequipement> findAll();

    List<Parametrecoutequipement> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Parametrecoutequipement> findAllRange();

    List<Parametrecoutequipement> findByTypeStructureTypeInfra(Typestructure typestructure, Typeequipementtraceur typeequipementtraceur, Etatequipement etatequipement) throws Exception;

}
