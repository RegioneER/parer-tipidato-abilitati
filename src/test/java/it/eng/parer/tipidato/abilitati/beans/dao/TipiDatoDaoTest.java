/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.tipidato.abilitati.beans.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import it.eng.parer.tipidato.abilitati.Profiles;
import it.eng.parer.tipidato.abilitati.beans.ITipiDatoDao;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.AppNameEnum;
import it.eng.parer.tipidato.abilitati.beans.utils.Costants.OrganizEnum;
import jakarta.inject.Inject;

@QuarkusTest
@TestProfile(Profiles.Core.class)
class TipiDatoDaoTest {

    static final String USERID = "test_microservizi"; // userid locale al db snap/test
    static final String NM_AMBIENTE = "PARER_PROVA"; // ambiente locale al db snap/test
    static final String NM_ENTE = "ente_test"; // ente locale al db snap/test
    static final String NM_STRUT = "PARER_TEST"; // struttura locale al db snap/test
    static final String NM_VERS = "AGENZIA_RICOSTRUZIONE"; // versatore locale al db snap/test

    @Inject
    ITipiDatoDao dao;

    @Test
    void findTipiDatoByStrut_ok() {
        assertDoesNotThrow(() -> dao.findTipiDato(AppNameEnum.SACER, USERID, OrganizEnum.AMBIENTE,
                NM_AMBIENTE, OrganizEnum.ENTE, NM_ENTE, OrganizEnum.STRUTTURA, NM_STRUT));
    }

    @Test
    void findTipiDatoByVers_ok() {
        assertDoesNotThrow(() -> dao.findTipiDato(AppNameEnum.SACER_PREINGEST, USERID, null, null,
                OrganizEnum.AMBIENTE, NM_AMBIENTE, OrganizEnum.VERSATORE, NM_VERS));
    }

}
