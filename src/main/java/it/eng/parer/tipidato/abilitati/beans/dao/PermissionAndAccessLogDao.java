/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package it.eng.parer.tipidato.abilitati.beans.dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import it.eng.parer.tipidato.abilitati.beans.IPermissionAndAccessLogDao;
import static it.eng.parer.tipidato.abilitati.beans.utils.Costants.SERVICE_NAME;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class PermissionAndAccessLogDao implements IPermissionAndAccessLogDao {

    private static final String IAM_AUTH_QUERY = " SELECT 1 FROM UsrUser iu JOIN iu.usrUsoUserApplics uuua "
            + " JOIN uuua.usrUsoRuoloUserDefaults uurud "
            + " JOIN uurud.prfRuolo pr JOIN pr.prfUsoRuoloApplics pura "
            + " JOIN pura.prfAutors autors JOIN autors.aplServizioWeb asw "
            + " WHERE uuua.aplApplic.nmApplic = 'SACER_IAM' "
            + " AND iu.nmUserid = :username AND asw.nmServizioWeb = :servizioWeb "
            + " AND iu.tipoUser = 'AUTOMA' ";

    private static final String LOG_ACCESSI_PARAM_QUERY = " SELECT paramApplic.nmParamApplic, valoreParamApplic.dsValoreParamApplic "
            + " FROM IamValoreParamApplic valoreParamApplic JOIN valoreParamApplic.iamParamApplic paramApplic "
            + " WHERE paramApplic.flAppartApplic = '1' AND valoreParamApplic.tiAppart = 'APPLIC' "
            + " AND paramApplic.tiParamApplic = :nmFunction ";

    @Inject
    EntityManager entityManager;

    @Override
    public boolean checkUserEnabledOnService(String nmUserid) {
        Query q = entityManager.createQuery(IAM_AUTH_QUERY);
        q.setParameter("username", nmUserid);
        q.setParameter("servizioWeb", SERVICE_NAME);
        return !q.getResultList().isEmpty();
    }

    @Override
    public HashMap<String, String> loadIdpLoggerParams(String nmFunction) {
        TypedQuery<Object[]> q = entityManager.createQuery(LOG_ACCESSI_PARAM_QUERY, Object[].class);
        q.setParameter("nmFunction", nmFunction);
        List<Object[]> list = q.getResultList();
        return (HashMap<String, String>) list.stream()
                .collect(Collectors.toMap(a -> (String) a[0], a -> (String) a[1]));
    }

}
