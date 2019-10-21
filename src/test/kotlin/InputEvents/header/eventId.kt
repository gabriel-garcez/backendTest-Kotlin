import com.github.guepardoapps.kulid.ULID
import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import org.apache.commons.lang3.RandomStringUtils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import org.apache.commons.lang3.RandomUtils.nextInt
import java.util.*


class InputEventEventId {
    @Test
    fun postEvent() {

        val randomUlid = ULID.random()
        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val httpRequest = RestAssured.given().relaxedHTTPSValidation()
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("correlationId", "1122334455");
        httpRequest.header("namespace", "individual.created");
        httpRequest.header("owner", "single-registry");
        httpRequest.header("CANONICALVERSION", "1");
        httpRequest.header("eventId", randomUlid);
        val file =
            File("C:\\Users\\Aluga.com\\IJProjects\\EventsProject\\src\\test\\resources\\individual.create-success_required.json")
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
        Assertions.assertThat(id).isEqualTo(randomUlid)

        //Try Again and should get error

        RestAssured.baseURI = "https://api.hom.eventcloud.gondor.infra/v1"
        val httpRequest2 = RestAssured.given().relaxedHTTPSValidation()
        httpRequest2.header("Content-Type", "application/json");
        httpRequest2.header("correlationId", "1122334455");
        httpRequest2.header("namespace", "individual.created");
        httpRequest2.header("owner", "single-registry");
        httpRequest2.header("CANONICALVERSION", "1");
        httpRequest2.header("eventId", randomUlid);
        val file2 =
            File("C:\\Users\\Aluga.com\\IJProjects\\EventsProject\\src\\test\\resources\\individual.create-success_required.json")
        httpRequest.body(file)
        val response2 = httpRequest2.post("/eventcloud/input")

        val statusCode2 = response2.statusCode
        val body2 = response2.body
        val bodyAsString2 = body2.asString()
        println("Response Body is: " + body2.asString())
        Assertions.assertThat(statusCode2).isEqualTo(400)
    }

}