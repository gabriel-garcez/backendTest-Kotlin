package healthcheck

import io.restassured.RestAssured
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HealthcheckComplete {
    @Test
    fun getHealthcheckComplete() {

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val httpRequest = RestAssured.given().relaxedHTTPSValidation()
        val response = httpRequest.get("/healthcheck/complete")
        val statusCode = response.statusCode
        val body = response.body
        val bodyAsString = body.asString()
        println("Response Body is: " + body.asString())
        Assertions.assertThat(statusCode).isEqualTo(200)
        println("Código Sucesso")
    }

}