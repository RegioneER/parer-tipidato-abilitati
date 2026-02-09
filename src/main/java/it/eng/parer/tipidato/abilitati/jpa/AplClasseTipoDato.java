/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.tipidato.abilitati.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the APL_CLASSE_TIPO_DATO database table.
 */
@Entity
@Table(name = "APL_CLASSE_TIPO_DATO")
public class AplClasseTipoDato implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idClasseTipoDato;

    private String nmClasseTipoDato;

    private AplApplic aplApplic;

    private List<UsrTipoDatoIam> usrTipoDatoIams = new ArrayList<>();

    public AplClasseTipoDato() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_CLASSE_TIPO_DATO")
    public Long getIdClasseTipoDato() {
	return this.idClasseTipoDato;
    }

    @Column(name = "NM_CLASSE_TIPO_DATO")
    public String getNmClasseTipoDato() {
	return this.nmClasseTipoDato;
    }

    // bi-directional many-to-one association to AplApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPLIC")
    public AplApplic getAplApplic() {
	return this.aplApplic;
    }

    // bi-directional many-to-one association to UsrTipoDatoIam
    @OneToMany(mappedBy = "aplClasseTipoDato")
    public List<UsrTipoDatoIam> getUsrTipoDatoIams() {
	return this.usrTipoDatoIams;
    }

    public void setUsrTipoDatoIams(List<UsrTipoDatoIam> usrTipoDatoIams) {
	this.usrTipoDatoIams = usrTipoDatoIams;
    }
}
