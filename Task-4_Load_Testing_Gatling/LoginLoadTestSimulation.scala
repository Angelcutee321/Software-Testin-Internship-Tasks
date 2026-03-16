import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoginLoadTestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://example.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8")
    .userAgentHeader("Mozilla/5.0")

  val scn = scenario("Load Test for Sample Web Application")
    .exec(
      http("Open Home Page")
        .get("/")
        .check(status.is(200))
    )
    .pause(2)
    .exec(
      http("Open Login Page")
        .get("/login")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("Submit Login Request")
        .post("/login")
        .formParam("username", "testuser")
        .formParam("password", "password123")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      rampUsers(50) during (20.seconds)
    )
  ).protocols(httpProtocol)
}
