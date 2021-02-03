/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Thematique;
import entities.ThematiqueActivite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Simo
 */
@Local
public interface ThematiqueActiviteFacadeLocal {

    void create(ThematiqueActivite thematiqueActivite);

    void edit(ThematiqueActivite thematiqueActivite);

    void remove(ThematiqueActivite thematiqueActivite);

    ThematiqueActivite find(Object id);

    List<ThematiqueActivite> findAll();

    List<ThematiqueActivite> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    ThematiqueActivite findbythematiqueActivite(int thematique, long activite);

    List<ThematiqueActivite> retour();

    List<ThematiqueActivite> findbythematique(int thematique);

    List<ThematiqueActivite> findAllRange();

    ThematiqueActivite findBythematiqueActv(int thematique, long activite) throws Exception;

    List<ThematiqueActivite> findBythematiques(int thematique) throws Exception;

    List<ThematiqueActivite> findByDistrict(District district) throws Exception;

    List<ThematiqueActivite> findByDistrictThematique(District district, Thematique thematique) throws Exception;

}
