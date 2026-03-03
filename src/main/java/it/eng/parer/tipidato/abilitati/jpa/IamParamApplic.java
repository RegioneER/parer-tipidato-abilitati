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
 * The persistent class for the IAM_PARAM_APPLIC database table.
 */
@Entity
@Table(name = "IAM_PARAM_APPLIC")
public class IamParamApplic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idParamApplic;

    private String flAppartApplic;

    private String nmParamApplic;

    private String tiParamApplic;

    private List<IamValoreParamApplic> iamValoreParamApplics = new ArrayList<>();

    public IamParamApplic() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_PARAM_APPLIC")
    public Long getIdParamApplic() {
        return this.idParamApplic;
    }

    @Column(name = "FL_APPART_APPLIC", columnDefinition = "char(1)")
    public String getFlAppartApplic() {
        return this.flAppartApplic;
    }

    @Column(name = "NM_PARAM_APPLIC")
    public String getNmParamApplic() {
        return this.nmParamApplic;
    }

    @Column(name = "TI_PARAM_APPLIC")
    public String getTiParamApplic() {
        return this.tiParamApplic;
    }

    // bi-directional many-to-one association to IamValoreParamApplic
    @OneToMany(mappedBy = "iamParamApplic")
    public List<IamValoreParamApplic> getIamValoreParamApplics() {
        return this.iamValoreParamApplics;
    }

}
