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
 * The persistent class for the APL_SERVIZIO_WEB database table.
 */
@Entity
@Table(name = "APL_SERVIZIO_WEB")
public class AplServizioWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idServizioWeb;

    private String nmServizioWeb;

    private AplApplic aplApplic;

    private List<PrfAutor> prfAutors = new ArrayList<>();

    public AplServizioWeb() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_SERVIZIO_WEB")
    public Long getIdServizioWeb() {
        return this.idServizioWeb;
    }

    @Column(name = "NM_SERVIZIO_WEB")
    public String getNmServizioWeb() {
        return this.nmServizioWeb;
    }

    // bi-directional many-to-one association to AplApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPLIC")
    public AplApplic getAplApplic() {
        return this.aplApplic;
    }

    // bi-directional many-to-one association to PrfAutor
    @OneToMany(mappedBy = "aplServizioWeb")
    public List<PrfAutor> getPrfAutors() {
        return this.prfAutors;
    }

}
