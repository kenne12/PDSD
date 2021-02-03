/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Medicamenttraceur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MedicamenttraceurFacadeLocal {

    void create(Medicamenttraceur medicamenttraceur);

    void edit(Medicamenttraceur medicamenttraceur);

    void remove(Medicamenttraceur medicamenttraceur);

    Medicamenttraceur find(Object id);

    List<Medicamenttraceur> findAll();

    List<Medicamenttraceur> findRange(int[] range);

    int count();

    int nextId();

    public List<Medicamenttraceur> findByNom(String nom);

    List<Medicamenttraceur> findAllRange();
}
