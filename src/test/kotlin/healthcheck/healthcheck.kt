package healthcheck

import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Healthcheck {
    @Test
    fun getHealthcheck() {

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val httpRequest = RestAssured.given().relaxedHTTPSValidation()
        val response = httpRequest.get("/healthcheck")
        val statusCode = response.statusCode
        val body = response.body
        val bodyAsString = body.asString()
        println("Response Body is: " + body.asString())
        assertThat(statusCode).isEqualTo(200)
        println("Código Sucesso")
        assertThat(bodyAsString).contains("OK")
        println("Retorno Sucesso")
    }

}