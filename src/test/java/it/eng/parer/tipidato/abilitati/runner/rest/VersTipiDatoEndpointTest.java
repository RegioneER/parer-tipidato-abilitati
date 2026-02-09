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

package it.eng.parer.tipidato.abilitati.runner.rest;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import it.eng.parer.tipidato.abilitati.Profiles;
import static it.eng.parer.tipidato.abilitati.beans.utils.Costants.COD_PERM_INTERNAL;
import static it.eng.parer.tipidato.abilitati.beans.utils.Costants.URL_GET_VERS_TIPIDATO;

@QuarkusTest
@TestProfile(Profiles.EndToEnd.class)
class VersTipiDatoEndpointTest {

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void successNoResult() {
	given().when().queryParam("amb", "NO-amb").queryParam("vers", "NO-vers")
		.get(URL_GET_VERS_TIPIDATO).then().statusCode(200).body("$", hasKey("totale"))
		.body("totale", is(0));
    }

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void successVers() {
	given().when().queryParam("amb", "PARER_PROVA").queryParam("vers", "AGENZIA_RICOSTRUZIONE")
		.get(URL_GET_VERS_TIPIDATO).then().statusCode(200).body("$", hasKey("totale"))
		.body("$", hasKey("tipiDato"));
    }

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void badAmbNoVers() {
	given().when().queryParam("amb", "PARER_PROVA").get(URL_GET_VERS_TIPIDATO).then()
		.statusCode(400).body("$", hasKey("BAD-REQUEST-ERROR-1"));
    }

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void badNoAmbVers() {
	given().when().queryParam("vers", "AGENZIA_RICOSTRUZIONE").get(URL_GET_VERS_TIPIDATO).then()
		.statusCode(400).body("$", hasKey("BAD-REQUEST-ERROR-1"));
    }

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void badNoAmbNoVers() {
	given().when().get(URL_GET_VERS_TIPIDATO).then().statusCode(400)
		.body("$", hasKey("BAD-REQUEST-ERROR-1")).body("$", hasKey("BAD-REQUEST-ERROR-2"));
    }

    @Test
    @TestSecurity(user = " ", roles = {
	    "versatore" })
    void badUserEmptyRequest() {
	given().when().get(URL_GET_VERS_TIPIDATO).then().statusCode(400);
    }

    @Test
    @TestSecurity(authorizationEnabled = true)
    void authNoTokenRequest() {
	given().when().get(URL_GET_VERS_TIPIDATO).then().statusCode(401);
    }

    @Test
    @TestSecurity(user = "fakeuser", roles = {
	    "fakerole" })
    void noAuthRequest() {
	given().when().get(URL_GET_VERS_TIPIDATO).then().statusCode(403);
    }

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "versatore" })
    void methodNotAllowed() {
	given().when().post(URL_GET_VERS_TIPIDATO).then().statusCode(405);
    }

    @Test
    @TestSecurity(user = "test_microservizi_no_abil", roles = {
	    "versatore" })
    void noAuthRequestBySIAM() {
	given().when().get(URL_GET_VERS_TIPIDATO).then().statusCode(401).body("$",
		hasKey(COD_PERM_INTERNAL));
    }
}
