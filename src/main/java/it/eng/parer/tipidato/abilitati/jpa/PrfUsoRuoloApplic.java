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
 * The persistent class for the PRF_USO_RUOLO_APPLIC database table.
 */
@Entity
@Table(name = "PRF_USO_RUOLO_APPLIC")
public class PrfUsoRuoloApplic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsoRuoloApplic;

    private List<PrfAutor> prfAutors = new ArrayList<>();

    private AplApplic aplApplic;

    private PrfRuolo prfRuolo;

    public PrfUsoRuoloApplic() { /* Hibernate */
    }

    @Id
    @Column(name = "ID_USO_RUOLO_APPLIC")
    public Long getIdUsoRuoloApplic() {
        return this.idUsoRuoloApplic;
    }

    // bi-directional many-to-one association to PrfAutor
    @OneToMany(mappedBy = "prfUsoRuoloApplic")
    public List<PrfAutor> getPrfAutors() {
        return this.prfAutors;
    }

    // bi-directional many-to-one association to AplApplic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_APPLIC")
    public AplApplic getAplApplic() {
        return this.aplApplic;
    }

    // bi-directional many-to-one association to PrfRuolo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RUOLO")
    public PrfRuolo getPrfRuolo() {
        return this.prfRuolo;
    }
}
