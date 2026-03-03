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
 * The persistent class for the USR_TIPO_DATO_IAM database table.
 */
@Entity
@Table(name = "USR_TIPO_DATO_IAM")
public class UsrTipoDatoIam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idTipoDatoIam;

    private String nmTipoDato;

    private List<UsrAbilDati> usrAbilDatis = new ArrayList<>();

    private AplClasseTipoDato aplClasseTipoDato;

    private UsrOrganizIam usrOrganizIam;

    public UsrTipoDatoIam() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_TIPO_DATO_IAM")
    public Long getIdTipoDatoIam() {
        return this.idTipoDatoIam;
    }

    @Column(name = "NM_TIPO_DATO")
    public String getNmTipoDato() {
        return this.nmTipoDato;
    }

    // bi-directional many-to-one association to AplClasseTipoDato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLASSE_TIPO_DATO")
    public AplClasseTipoDato getAplClasseTipoDato() {
        return this.aplClasseTipoDato;
    }

    // bi-directional many-to-one association to UsrAbilDati
    @OneToMany(mappedBy = "usrTipoDatoIam")
    public List<UsrAbilDati> getUsrAbilDatis() {
        return this.usrAbilDatis;
    }

    // bi-directional many-to-one association to UsrOrganizIam
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORGANIZ_IAM")
    public UsrOrganizIam getUsrOrganizIam() {
        return this.usrOrganizIam;
    }

}
