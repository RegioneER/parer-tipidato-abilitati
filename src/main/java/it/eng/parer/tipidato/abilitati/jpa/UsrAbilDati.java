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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The persistent class for the USR_ABIL_DATI database table.
 */
@Entity
@Table(name = "USR_ABIL_DATI")
public class UsrAbilDati implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idAbilDati;

    private UsrTipoDatoIam usrTipoDatoIam;

    private UsrUsoUserApplic usrUsoUserApplic;

    public UsrAbilDati() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_ABIL_DATI")
    public Long getIdAbilDati() {
        return this.idAbilDati;
    }

    // bi-directional many-to-one association to UsrTipoDatoIam
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_DATO_IAM")
    public UsrTipoDatoIam getUsrTipoDatoIam() {
        return this.usrTipoDatoIam;
    }

    // bi-directional many-to-one association to UsrUsoUserApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USO_USER_APPLIC")
    public UsrUsoUserApplic getUsrUsoUserApplic() {
        return this.usrUsoUserApplic;
    }

}
