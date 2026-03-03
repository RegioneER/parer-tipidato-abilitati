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
 * The persistent class for the USR_USER database table.
 */
@Entity
@Table(name = "USR_USER")
public class UsrUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUserIam;

    private String nmUserid;

    private String tipoUser;

    private List<UsrUsoUserApplic> usrUsoUserApplics = new ArrayList<>();

    public UsrUser() {/* Hibernate */
    }

    @Id
    @Column(name = "ID_USER_IAM")
    public Long getIdUserIam() {
        return this.idUserIam;
    }

    @Column(name = "NM_USERID")
    public String getNmUserid() {
        return this.nmUserid;
    }

    @Column(name = "TIPO_USER")
    public String getTipoUser() {
        return this.tipoUser;
    }

    // bi-directional many-to-one association to UsrUsoUserApplic
    @OneToMany(mappedBy = "usrUser")
    public List<UsrUsoUserApplic> getUsrUsoUserApplics() {
        return this.usrUsoUserApplics;
    }

}
