import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class NotSendEvent {
    @Test
    fun postStatus(){

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val StatusRequest = RestAssured.given().relaxedHTTPSValidation()
        StatusRequest.header("eventId", "XXXRT");
        val response = StatusRequest.post("/eventcloud/status")
        val statusCode = response.statusCode
        val body = response.body
        val bodyAsString = body.asString()
        println("Response Body is: " + body.asString())
        Assertions.assertThat(statusCode).isEqualTo(200)
        val js2 = JsonPath(bodyAsString)
        val status = js2.get<String>("status")
        Assertions.assertThat(status).isEqualTo("event is not sent")

    }
}
