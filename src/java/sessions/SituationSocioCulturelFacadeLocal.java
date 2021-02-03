/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.SituationSocioCulturel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Local
public interface SituationSocioCulturelFacadeLocal {

    void create(SituationSocioCulturel situationSocioCulturel);

    void edit(SituationSocioCulturel situationSocioCulturel);

    void remove(SituationSocioCulturel situationSocioCulturel);

    SituationSocioCulturel find(Object id);

    List<SituationSocioCulturel> findAll();

    List<SituationSocioCulturel> findRange(int[] range);

    int count();
    public int nextId();

    public List<SituationSocioCulturel> findByEducation(String introduction);

    public List<SituationSocioCulturel> findByDistrict(int district);

}
