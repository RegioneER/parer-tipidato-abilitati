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

import java.util.stream.Stream;

import org.hibernate.jpa.HibernateHints;

import it.eng.parer.tipidato.abilitati.beans.ITipiDatoDao;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.AppNameEnum;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.OrganizEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class TipiDatoDao implements ITipiDatoDao {

    private static final String FIND_TIPI_DATO_QUERY_BASE = "SELECT classeTipoDato.nmClasseTipoDato, tipoDatoIam.nmTipoDato FROM UsrAbilDati abilDati "
            + " JOIN abilDati.usrTipoDatoIam tipoDatoIam JOIN abilDati.usrUsoUserApplic usoUserApplic "
            + " JOIN usoUserApplic.usrUser utente JOIN usoUserApplic.aplApplic applic "
            + " JOIN tipoDatoIam.aplClasseTipoDato classeTipoDato JOIN tipoDatoIam.usrOrganizIam organizIam "
            + " JOIN organizIam.aplTipoOrganiz tipoOrganiz "
            + " JOIN organizIam.usrOrganizIamPadre organizIamPadre "
            + " JOIN organizIamPadre.aplTipoOrganiz tipoOrganizPadre ";

    private static final String FIND_TIPI_DATO_QUERY_ADD_WHERE_COND = " WHERE applic.nmApplic = :nmApplic "
            + " AND utente.nmUserid = :nmUserid "
            + " AND tipoOrganiz.nmTipoOrganiz = :nmTipoOrganiz "
            + " AND organizIam.nmOrganiz = :nmOrganiz "
            + " AND organizIamPadre.nmOrganiz = :nmOrganizPadre "
            + " AND tipoOrganizPadre.nmTipoOrganiz = :nmTipoOrganizPadre ";

    private static final String FIND_TIPI_DATO_QUERY_ADD_THIRD_LEVEL_JOIN = " JOIN organizIamPadre.usrOrganizIamPadre organizIamNonno "
            + " JOIN organizIamNonno.aplTipoOrganiz tipoOrganizNonno ";

    private static final String FIND_TIPI_DATO_QUERY_ADD_THIRD_LEVEL_WHERE_COND = " AND organizIamNonno.nmOrganiz = :nmOrganizNonno "
            + " AND tipoOrganizNonno.nmTipoOrganiz = :nmTipoOrganizNonno ";

    @Inject
    EntityManager entityManager;

    // strad fac
    @Override
    public Stream<Object[]> findTipiDato(AppNameEnum nmApplic, String nmUserid,
            OrganizEnum nmTipoOrganizNonno, String nmOrganizNonno, OrganizEnum nmTipoOrganizPadre,
            String nmOrganizPadre, OrganizEnum nmTipoOrganiz, String nmOrganiz) {

        StringBuilder queryTot = new StringBuilder(FIND_TIPI_DATO_QUERY_BASE);
        if (nmOrganizNonno != null) {
            queryTot.append(FIND_TIPI_DATO_QUERY_ADD_THIRD_LEVEL_JOIN)
                    .append(FIND_TIPI_DATO_QUERY_ADD_WHERE_COND)
                    .append(FIND_TIPI_DATO_QUERY_ADD_THIRD_LEVEL_WHERE_COND);
        } else {
            queryTot.append(FIND_TIPI_DATO_QUERY_ADD_WHERE_COND);
        }

        TypedQuery<Object[]> query = entityManager.createQuery(queryTot.toString(), Object[].class);

        // hibernate hint
        query.setHint(HibernateHints.HINT_READ_ONLY, true);
        query.setHint(HibernateHints.HINT_CACHEABLE, true);

        // params
        query.setParameter("nmApplic", nmApplic.name());
        query.setParameter("nmUserid", nmUserid);
        query.setParameter("nmTipoOrganiz", nmTipoOrganiz.name());
        query.setParameter("nmOrganiz", nmOrganiz);
        query.setParameter("nmTipoOrganizPadre", nmTipoOrganizPadre.name());
        query.setParameter("nmOrganizPadre", nmOrganizPadre);
        if (nmOrganizNonno != null) {
            query.setParameter("nmTipoOrganizNonno", nmTipoOrganizNonno.name());
            query.setParameter("nmOrganizNonno", nmOrganizNonno);
        }

        return query.getResultStream();
    }

}
