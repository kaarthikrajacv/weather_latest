package org.example.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

public class ReqResExampleDefinition {

    RequestSpecification request;

    Response response;

    @Given("Input details for POST request")
    public void input_details_for_post_request() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "https://reqres.in/";
        request = RestAssured.given();

        Header reqHeader = new Header("Content-Type", "application/json");
        JSONObject reqBody = new JSONObject();

        reqBody.put("name", "Kaarthik");
        reqBody.put("job", "API Trainer").put("key", "value");
        //Converting req body as a String and setting in request...
        request.header(reqHeader);
        request.body(reqBody.toString());
    }

    @When("Submit the POST request")
    public void submit_the_post_request() {
        // Trigger API POST request...
        response = request.post("api/users");
    }
    @Then("Verify the POST response")
    public void verify_the_post_response() {

        //Assert status code and a field
        Assert.assertEquals(201, response.statusCode());
        response.prettyPrint();
        Assert.assertEquals("Kaarthik", response.jsonPath().get("name"));

    }

}
