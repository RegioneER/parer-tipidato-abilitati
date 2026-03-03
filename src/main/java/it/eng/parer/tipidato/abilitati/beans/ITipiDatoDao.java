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

package it.eng.parer.tipidato.abilitati.beans;

import java.util.stream.Stream;

import it.eng.parer.tipidato.abilitati.beans.utils.Costants.AppNameEnum;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.OrganizEnum;

public interface ITipiDatoDao {

    /**
     * Restituisce la stream con lista dei tipi dato abilitati
     *
     * @param nmApplic           applicazione alla quale fa riferimento l'organizzazione di cui
     *                           ricavare i tipi dato
     * @param nmUserid           utente che richiede l'elenco dei tipi dato
     * @param nmTipoOrganizNonno il tipo di organizzazione nonno
     * @param nmOrganizNonno     il nome dell'organizzazione nonno
     * @param nmTipoOrganizPadre il tipo di organizzazione padre
     * @param nmOrganizPadre     il nome dell'organizzazione padre
     * @param nmTipoOrganiz      il tipo di organizzazione di ultimo livello (struttura sacer o
     *                           versatore ping) di cui si vuole recuperare i tipi dato
     * @param nmOrganiz          il nome dell'organizzazione di ultimo livello di cui si vuole
     *                           recuperare i tipi dato
     *
     *
     * @return un array di Object contenente i dati dei tipi dato
     */
    Stream<Object[]> findTipiDato(AppNameEnum nmApplic, String nmUserid,
            OrganizEnum nmTipoOrganizNonno, String nmOrganizNonno, OrganizEnum nmTipoOrganizPadre,
            String nmOrganizPadre, OrganizEnum nmTipoOrganiz, String nmOrganiz);

}
