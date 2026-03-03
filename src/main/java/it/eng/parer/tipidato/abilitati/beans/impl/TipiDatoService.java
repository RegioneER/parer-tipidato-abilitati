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
package it.eng.parer.tipidato.abilitati.beans.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.parer.tipidato.abilitati.beans.ITipiDatoDao;
import it.eng.parer.tipidato.abilitati.beans.ITipiDatoService;
import it.eng.parer.tipidato.abilitati.beans.dto.TipoDatoDto;
import it.eng.parer.tipidato.abilitati.beans.exceptions.AppGenericRuntimeException;
import it.eng.parer.tipidato.abilitati.beans.exceptions.ErrorCategory;
import it.eng.parer.tipidato.abilitati.beans.model.TipiDatoResponse;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.AppNameEnum;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.OrganizEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
public class TipiDatoService implements ITipiDatoService {

    private static final Logger log = LoggerFactory.getLogger(TipiDatoService.class);

    @Inject
    ITipiDatoDao orgDao;

    @Override
    @Transactional(value = TxType.REQUIRED, rollbackOn = {
            AppGenericRuntimeException.class })
    public TipiDatoResponse listTipiDatoByStrut(String nmUserid, String nmAmbiente, String nmEnte,
            String nmStrut, String uri) {
        String struttura = nmAmbiente + " / " + nmEnte + " / " + nmStrut;
        try {
            // Inizializzo le informazioni da restituire
            List<TipoDatoDto> dtoList = new ArrayList<>();
            Integer numLastLevelOrg = elabTipiDato(nmUserid, AppNameEnum.SACER,
                    OrganizEnum.AMBIENTE, nmAmbiente, OrganizEnum.ENTE, nmEnte,
                    OrganizEnum.STRUTTURA, nmStrut, dtoList);

            log.atInfo().log("TipiDatoAbilitati - Recuperati {} tipi dato per la struttura {}",
                    numLastLevelOrg, struttura);
            // Ritorna la response
            return new TipiDatoResponse(struttura, null, dtoList, numLastLevelOrg, uri);

        } catch (Exception e) {
            throw AppGenericRuntimeException.builder().category(ErrorCategory.INTERNAL_ERROR)
                    .cause(e)
                    .message("Errore estrazione lista tipi dato per nmUserid {0} e struttura {1}",
                            nmUserid, struttura)
                    .build();
        }
    }

    @Override
    @Transactional(value = TxType.REQUIRED, rollbackOn = {
            AppGenericRuntimeException.class })
    public TipiDatoResponse listTipiDatoByVers(String nmUserid, String nmAmbiente, String nmVers,
            String uri) {
        String versatore = nmAmbiente + " / " + nmVers;
        try {
            // Inizializzo le informazioni da restituire
            List<TipoDatoDto> dtoList = new ArrayList<>();
            Integer numLastLevelOrg = elabTipiDato(nmUserid, AppNameEnum.SACER_PREINGEST, null,
                    null, OrganizEnum.AMBIENTE, nmAmbiente, OrganizEnum.VERSATORE, nmVers, dtoList);

            log.atInfo().log("TipiDatoAbilitati - Recuperati {} tipi dato per il versatore {}",
                    numLastLevelOrg, versatore);
            // Ritorna la response
            return new TipiDatoResponse(null, versatore, dtoList, numLastLevelOrg, uri);

        } catch (Exception e) {
            throw AppGenericRuntimeException.builder().category(ErrorCategory.INTERNAL_ERROR)
                    .cause(e)
                    .message("Errore estrazione lista tipi dato per nmUserid {0} e versatore {1}",
                            nmUserid, versatore)
                    .build();
        }
    }

    /**
     * Ricava i tipi dato dell'organizzazione richiesta
     *
     * @param nmUserid           id utente che invoca il servizio
     * @param nmTipoOrganizNonno il tipo di organizzazione nonno {@link OrganizEnum}
     * @param nmOrganizNonno     il nome dell'organizzazione nonno
     * @param nmTipoOrganizPadre il tipo di organizzazione padre {@link OrganizEnum}
     * @param nmOrganizPadre     il nome dell'organizzazione padre
     * @param nmTipoOrganiz      il tipo di organizzazione di ultimo livello (struttura sacer o
     *                           versatore ping) di cui si vuole recuperare i tipi dato
     *                           {@link OrganizEnum}
     * @param nmOrganiz          il nome dell'organizzazione di ultimo livello di cui si vuole
     *                           recuperare i tipi dato
     * @param dtoList            lista dei tipi dato da popolare
     *
     * @return numLastLevelOrg totale risultati
     */
    private Integer elabTipiDato(String nmUserid, AppNameEnum applic,
            OrganizEnum nmTipoOrganizNonno, String nmOrganizNonno, OrganizEnum nmTipoOrganizPadre,
            String nmOrganizPadre, OrganizEnum nmTipoOrganiz, String nmOrganiz,
            List<TipoDatoDto> dtoList) {
        log.atInfo().log("TipiDatoAbilitati - Recupero i tipi dato abilitati all'organizzazione {}",
                nmOrganizNonno + " / " + nmOrganizPadre + " / " + nmOrganiz);
        // Utilizzo una treemap per sfruttare l'ordinamento naturale
        Map<String, List<String>> mappaTipiDato = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        // Recupero i tipi dato dell'organizzazione
        Stream<Object[]> result = orgDao.findTipiDato(applic, nmUserid, nmTipoOrganizNonno,
                nmOrganizNonno, nmTipoOrganizPadre, nmOrganizPadre, nmTipoOrganiz, nmOrganiz);
        // Li "organizzo" in oggetti Map e ricavo il numero di tipi dato
        int totale = getTipiDatoMap(result, mappaTipiDato);
        // Passaggio jpa -> dto
        dtoList.addAll(populateTipoDato(mappaTipiDato));
        return totale;
    }

    /**
     * Data una lista di oggetti rappresentanti i tipi dato, ne restituisce una mappa
     *
     * @param tipiDatoAsStream lista dei tipi dato cui l'utente è abilitato
     * @param mappaTipiDato    mappa dei tipi dato
     *
     * @return totale
     */
    private int getTipiDatoMap(Stream<Object[]> tipiDatoAsStream,
            Map<String, List<String>> mappaTipiDato) {
        AtomicInteger numTipiDatoStruttura = new AtomicInteger();
        // forEach
        tipiDatoAsStream.forEach(tipoDato -> {
            // Codice tipo dato: REGISTRO, TIPO_UD, TIPO_DOC...
            String cdTipoDato = (String) tipoDato[0];
            // Nome del tipo dato
            String nmTipoDato = (String) tipoDato[1];
            List<String> listaNomiTipiDato = new ArrayList<>();

            if (mappaTipiDato.containsKey(cdTipoDato)) {
                listaNomiTipiDato = mappaTipiDato.get(cdTipoDato);
            }

            listaNomiTipiDato.add(nmTipoDato);
            numTipiDatoStruttura.incrementAndGet();
            Collections.sort(listaNomiTipiDato);
            mappaTipiDato.put(cdTipoDato, listaNomiTipiDato);
        });
        return numTipiDatoStruttura.get();
    }

    /**
     * Converte la mappa dei tipi dato in oggetti DTO
     *
     * @param mappaTipiDato mappa dei tipi dato abilitati
     *
     * @return Lista di {@link TipoDatoDto} contenente i tipi dato abilitati
     */
    private List<TipoDatoDto> populateTipoDato(Map<String, List<String>> mappaTipiDato) {
        List<TipoDatoDto> listaTipiDatoDto = new ArrayList<>();
        for (Map.Entry<String, List<String>> tipiDatoStrutturaEntry : mappaTipiDato.entrySet()) {
            // Codice tipo dato: REGISTRO, TIPO_UD, TIPO_DOC, TIPO_OBJECT...
            String cdTipoDato = tipiDatoStrutturaEntry.getKey();
            // Lista dei valori
            List<String> listaNomiTipiDato = tipiDatoStrutturaEntry.getValue();
            // Creo il dto
            TipoDatoDto tipoDatoDto = new TipoDatoDto(cdTipoDato, listaNomiTipiDato);
            // Lo aggiungo alla lista dei dto
            listaTipiDatoDto.add(tipoDatoDto);
        }
        return listaTipiDatoDto;
    }

}
