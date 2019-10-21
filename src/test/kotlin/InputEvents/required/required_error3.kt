import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class InputEventWithoutAddressData {
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
            File("C:\\Users\\Aluga.com\\IJProjects\\EventsProject\\src\\test\\resources\\individual.create-must_error3.json")
        httpRequest.body(file)
        val response = httpRequest.post("/eventcloud/input")

        val statusCode = response.statusCode
        val body = response.body
        val bodyAsString = body.asString()
        println("Response Body is: " + body.asString())
        Assertions.assertThat(statusCode).isEqualTo(422)
    }

}