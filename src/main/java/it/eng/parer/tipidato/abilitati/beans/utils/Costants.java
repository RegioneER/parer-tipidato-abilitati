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

package it.eng.parer.tipidato.abilitati.beans.utils;

public class Costants {

    private Costants() {
        throw new IllegalStateException("Utility class");
    }

    // nome servizio
    public static final String SERVICE_NAME = "TipiDatoAbilitatiMach";
    // IDP log accessi
    public static final String IDP_LOG_ACESSI_FUNCT = "Log accessi";
    // Costanti per il log dei login ws e la disattivazione automatica utenti
    public static final String IDP_MAX_TENTATIVI_FALLITI = "MAX_TENTATIVI_FALLITI";
    public static final String IDP_MAX_GIORNI = "MAX_GIORNI";
    public static final String IDP_QRY_DISABLE_USER = "QRY_DISABLE_USER";
    public static final String IDP_QRY_VERIFICA_DISATTIVAZIONE_UTENTE = "QRY_VERIFICA_DISATTIVAZIONE_UTENTE";
    public static final String IDP_QRY_REGISTRA_EVENTO_UTENTE = "QRY_REGISTRA_EVENTO_UTENTE";

    // URI endpoint
    public static final String URL_ADMIN_BASE = "/admin";
    public static final String URL_API_BASE = "/api";
    public static final String URL_API_STRUT = URL_API_BASE + "/strut";
    public static final String URL_API_VERS = URL_API_BASE + "/vers";

    public static final String RESOURCE_INFOS = "/infos";
    public static final String RESOURCE_TIPIDATO = "/tipidato";

    //
    public static final String URL_GET_STRUT_TIPIDATO = URL_API_STRUT + RESOURCE_TIPIDATO;
    public static final String URL_GET_VERS_TIPIDATO = URL_API_VERS + RESOURCE_TIPIDATO;
    public static final String URL_GET_STATUS = "/status";

    public static final String URL_GET_INFOS = URL_ADMIN_BASE + RESOURCE_INFOS;

    public static final String MDC_LOG_UUID = "log_uuid";

    // error code
    public static final String COD_ERR_INTERNAL = "GENERIC-ERROR";
    public static final String COD_ERR_BADREQ = "BAD-REQUEST-ERROR";
    public static final String COD_PERM_INTERNAL = "PERMISSION-ERROR";

    public enum AppNameEnum {
        ANY, SACER, SACER_PREINGEST
    }

    public enum OrganizEnum {
        AMBIENTE, ENTE, STRUTTURA, VERSATORE
    }

}
