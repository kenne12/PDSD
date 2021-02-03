/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquehospitalisation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RubriquehospitalisationFacadeLocal {

    void create(Rubriquehospitalisation rubriquehospitalisation);

    void edit(Rubriquehospitalisation rubriquehospitalisation);

    void remove(Rubriquehospitalisation rubriquehospitalisation);

    Rubriquehospitalisation find(Object id);

    List<Rubriquehospitalisation> findAll();

    List<Rubriquehospitalisation> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Rubriquehospitalisation> findByNom(String nom);
    
    public List<Rubriquehospitalisation> findAllRange() ;
}
