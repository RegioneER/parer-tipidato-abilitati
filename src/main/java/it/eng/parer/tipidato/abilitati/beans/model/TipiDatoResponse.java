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

package it.eng.parer.tipidato.abilitati.beans.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.eng.parer.tipidato.abilitati.beans.dto.TipoDatoDto;

@JsonInclude(Include.NON_NULL)
public class TipiDatoResponse {

    // multi request
    private String path;
    private Integer totale;
    private String struttura;
    private String versatore;
    private List<TipoDatoDto> tipiDato;

    public TipiDatoResponse() {
	super();
    }

    public TipiDatoResponse(String struttura, String versatore, List<TipoDatoDto> tipiDato,
	    Integer totaleTipiDato, String uri) {
	super();
	this.struttura = struttura;
	this.versatore = versatore;
	this.tipiDato = tipiDato;
	this.totale = totaleTipiDato;
	this.path = uri;
    }

    public String getPath() {
	return path;
    }

    public Integer getTotale() {
	return totale;
    }

    public List<TipoDatoDto> getTipiDato() {
	return tipiDato;
    }

    public String getStruttura() {
	return struttura;
    }

    public String getVersatore() {
	return versatore;
    }

}
