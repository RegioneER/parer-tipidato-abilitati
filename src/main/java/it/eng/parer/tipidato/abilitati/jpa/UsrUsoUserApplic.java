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
 * The persistent class for the USR_USO_USER_APPLIC database table.
 */
@Entity
@Table(name = "USR_USO_USER_APPLIC")
public class UsrUsoUserApplic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsoUserApplic;

    private AplApplic aplApplic;

    private UsrUser usrUser;

    private List<UsrAbilOrganiz> usrAbilOrganizs = new ArrayList<>();

    private List<UsrUsoRuoloUserDefault> usrUsoRuoloUserDefaults = new ArrayList<>();

    public UsrUsoUserApplic() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_USO_USER_APPLIC")
    public Long getIdUsoUserApplic() {
        return this.idUsoUserApplic;
    }

    public void setIdUsoUserApplic(Long idUsoUserApplic) {
        this.idUsoUserApplic = idUsoUserApplic;
    }

    // bi-directional many-to-one association to UsrAbilOrganiz
    @OneToMany(mappedBy = "usrUsoUserApplic")
    public List<UsrAbilOrganiz> getUsrAbilOrganizs() {
        return this.usrAbilOrganizs;
    }

    public void setUsrAbilOrganizs(List<UsrAbilOrganiz> usrAbilOrganizs) {
        this.usrAbilOrganizs = usrAbilOrganizs;
    }

    // bi-directional many-to-one association to AplApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPLIC")
    public AplApplic getAplApplic() {
        return this.aplApplic;
    }

    public void setAplApplic(AplApplic aplApplic) {
        this.aplApplic = aplApplic;
    }

    // bi-directional many-to-one association to UsrUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_IAM")
    public UsrUser getUsrUser() {
        return this.usrUser;
    }

    public void setUsrUser(UsrUser usrUser) {
        this.usrUser = usrUser;
    }

    // bi-directional many-to-one association to UsrUsoRuoloUserDefault
    @OneToMany(mappedBy = "usrUsoUserApplic")
    public List<UsrUsoRuoloUserDefault> getUsrUsoRuoloUserDefaults() {
        return this.usrUsoRuoloUserDefaults;
    }

    public void setUsrUsoRuoloUserDefaults(List<UsrUsoRuoloUserDefault> usrUsoRuoloUserDefaults) {
        this.usrUsoRuoloUserDefaults = usrUsoRuoloUserDefaults;
    }
}
