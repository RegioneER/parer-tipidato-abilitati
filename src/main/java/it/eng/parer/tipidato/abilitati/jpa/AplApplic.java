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
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the APL_APPLIC database table.
 */
@Entity
@Table(name = "APL_APPLIC")
public class AplApplic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idApplic;

    private String nmApplic;

    private List<AplTipoOrganiz> aplTipoOrganizs = new ArrayList<>();

    private List<UsrOrganizIam> usrOrganizIams = new ArrayList<>();

    private List<UsrUsoUserApplic> usrUsoUserApplics = new ArrayList<>();

    public AplApplic() {
        /* Hibernate */
    }

    @Id
    @Column(name = "ID_APPLIC")
    public Long getIdApplic() {
        return this.idApplic;
    }

    @Column(name = "NM_APPLIC")
    public String getNmApplic() {
        return this.nmApplic;
    }

    // bi-directional many-to-one association to AplTipoOrganiz
    @OneToMany(mappedBy = "aplApplic")
    public List<AplTipoOrganiz> getAplTipoOrganizs() {
        return this.aplTipoOrganizs;
    }

    // bi-directional many-to-one association to UsrOrganizIam
    @OneToMany(mappedBy = "aplApplic")
    public List<UsrOrganizIam> getUsrOrganizIams() {
        return this.usrOrganizIams;
    }

    // bi-directional many-to-one association to UsrUsoUserApplic
    @OneToMany(mappedBy = "aplApplic")
    public List<UsrUsoUserApplic> getUsrUsoUserApplics() {
        return this.usrUsoUserApplics;
    }

}
