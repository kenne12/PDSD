/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Interventionpnds;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface InterventionpndsFacadeLocal {

    void create(Interventionpnds interventionpnds);

    void edit(Interventionpnds interventionpnds);

    void remove(Interventionpnds interventionpnds);

    Interventionpnds find(Object id);

    List<Interventionpnds> findAll();

    List<Interventionpnds> findRange(int[] range);

    int count();

    int nextId();

    public List<Interventionpnds> findByNom(String nom);

    List<Interventionpnds> findAllRange();

    List<Interventionpnds> findAllRangeAll();

    List<Interventionpnds> findAllRangeCode();

    List<Interventionpnds> findBySousaxe(Sousaxe sousaxe) throws Exception;

}
