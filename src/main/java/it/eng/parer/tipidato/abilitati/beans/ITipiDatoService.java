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

import it.eng.parer.tipidato.abilitati.beans.model.TipiDatoResponse;
import jakarta.validation.constraints.NotBlank;

public interface ITipiDatoService {

    /**
     * Restituisce la lista dei tipi dato abilitati
     *
     * @param userId     userdId utente associato ai tipi dato abilitati
     * @param nmAmbiente nome ambiente SACER
     * @param nmEnte     nome ente SACER
     * @param nmStrut    nome struttura SACER
     * @param uri        absolute uri
     *
     * @return la response decorata (vedi {@link TipiDatoResponse})
     */
    TipiDatoResponse listTipiDatoByStrut(
	    @NotBlank(message = "userId non valorizzato") String userId, String nmAmbiente,
	    String nmEnte, String nmStrut, String uri);

    /**
     * Restituisce la lista dei tipi dato abilitati
     *
     * @param userId     userdId utente associato ai tipi dato abilitati
     * @param nmAmbiente nome ambiente PING
     * @param nmVers     nome versatore PING
     * @param uri        absolute uri
     *
     * @return la response decorata (vedi {@link TipiDatoResponse})
     */
    TipiDatoResponse listTipiDatoByVers(@NotBlank(message = "userId non valorizzato") String userId,
	    String nmAmbiente, String nmVers, String uri);

}
