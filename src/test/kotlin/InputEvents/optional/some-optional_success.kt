import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class InputEventSomeOptional {
    @Test
    fun postEvent() {

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val httpRequest = RestAssured.given().relaxedHTTPSValidation()
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("correlationId", "1122334455");
        httpRequest.header("namespace", "individual.created");
        httpRequest.header("owner", "single-registry");
        httpRequest.header("CANONICALVERSION", "1");
        val file =
            File("C:\\Users\\Aluga.com\\IJProjects\\EventsProject\\src\\test\\resources\\individual.create-some_optional.json")
        httpRequest.body(file)
        val response = httpRequest.post("/eventcloud/input")

        val statusCode = response.statusCode
        val body = response.body
        val bodyAsString = body.asString()
        println("Response Body is: " + body.asString())
        Assertions.assertThat(statusCode).isEqualTo(201)
        Assertions.assertThat(bodyAsString).contains("id")

        val js = JsonPath(bodyAsString)
        val id = js.get<String>("id")
        println(id)

        //Check Event Status

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val StatusRequest2 = RestAssured.given().relaxedHTTPSValidation()
        StatusRequest2.header("eventId", id);
        val responseStatus = StatusRequest2.post("/eventcloud/status")

        val statusCodeStatus = responseStatus.statusCode
        val Statusbody = responseStatus.body
        val StatusbodyAsString = Statusbody.asString()
        println("Response Body is: " + Statusbody.asString())
        Assertions.assertThat(statusCodeStatus).isEqualTo(409)
        val js2 = JsonPath(StatusbodyAsString)
        val status = js2.get<String>("status")
        Assertions.assertThat(status).isEqualTo("event already sent")
    }

}