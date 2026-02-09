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
 * The persistent class for the APL_TIPO_ORGANIZ database table.
 */
@Entity
@Table(name = "APL_TIPO_ORGANIZ")
public class AplTipoOrganiz implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idTipoOrganiz;

    private String nmTipoOrganiz;

    private AplApplic aplApplic;

    private List<UsrOrganizIam> usrOrganizIams = new ArrayList<>();

    public AplTipoOrganiz() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_TIPO_ORGANIZ")
    public Long getIdTipoOrganiz() {
	return this.idTipoOrganiz;
    }

    @Column(name = "NM_TIPO_ORGANIZ")
    public String getNmTipoOrganiz() {
	return this.nmTipoOrganiz;
    }

    // bi-directional many-to-one association to AplApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPLIC")
    public AplApplic getAplApplic() {
	return this.aplApplic;
    }

    // bi-directional many-to-one association to UsrOrganizIam
    @OneToMany(mappedBy = "aplTipoOrganiz")
    public List<UsrOrganizIam> getUsrOrganizIams() {
	return this.usrOrganizIams;
    }

}
