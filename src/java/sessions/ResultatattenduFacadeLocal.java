/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Indicateur;
import entities.Resultatattendu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ResultatattenduFacadeLocal {

    void create(Resultatattendu resultatattendu);

    void edit(Resultatattendu resultatattendu);

    void remove(Resultatattendu resultatattendu);

    Resultatattendu find(Object id);

    List<Resultatattendu> findAll();

    List<Resultatattendu> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Resultatattendu> findByIndicateur(Indicateur indicateur) throws Exception;
    
    List<Resultatattendu> findAllRange();

}
