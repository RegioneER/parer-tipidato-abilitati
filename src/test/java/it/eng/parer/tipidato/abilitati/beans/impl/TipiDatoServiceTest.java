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

package it.eng.parer.tipidato.abilitati.beans.impl;

import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import it.eng.parer.tipidato.abilitati.Profiles;
import it.eng.parer.tipidato.abilitati.beans.ITipiDatoService;
import it.eng.parer.tipidato.abilitati.beans.model.TipiDatoResponse;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;

@QuarkusTest
@TestProfile(Profiles.Core.class)
class TipiDatoServiceTest {

    static final String USERID = "test_microservizi"; // userid locale al db snap/test
    static final String NM_AMBIENTE = "PARER_PROVA"; // ambiente locale al db snap/test
    static final String NM_ENTE = "ente_test"; // ente locale al db snap/test
    static final String NM_STRUT = "PARER_TEST"; // struttura locale al db snap/test
    static final String NM_VERS = "AGENZIA_RICOSTRUZIONE"; // versatore locale al db snap/test

    @Inject
    ITipiDatoService service;

    @Test
    void listTipiDatoByStrut_ok() {
	TipiDatoResponse result = assertDoesNotThrow(() -> service.listTipiDatoByStrut(USERID,
		NM_AMBIENTE, NM_ENTE, NM_STRUT, StringUtils.EMPTY));
	assertTrue(!result.getTipiDato().isEmpty());
    }

    @Test
    void listTipiDatoByStrutEmptyUserid_ko() {
	assertThrows(ConstraintViolationException.class, () -> service
		.listTipiDatoByStrut(StringUtils.EMPTY, null, null, null, StringUtils.EMPTY));
    }

    @Test
    void listTipiDatoByVers_ok() {
	TipiDatoResponse result = assertDoesNotThrow(
		() -> service.listTipiDatoByVers(USERID, NM_AMBIENTE, NM_VERS, StringUtils.EMPTY));
	assertTrue(!result.getTipiDato().isEmpty());
    }

    @Test
    void listTipiDatoByVersEmptyUserid_ko() {
	assertThrows(ConstraintViolationException.class,
		() -> service.listTipiDatoByVers(StringUtils.EMPTY, null, null, StringUtils.EMPTY));
    }

}
