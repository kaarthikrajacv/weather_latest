package org.example.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class WeatherExampleDefinition {

    RequestSpecification weatherRequest;

    Response weatherResponse;

    @Before
    public void startup(){

        System.out.println("This is BEFORE Method");
    }

    @After
    public void tearDown(){
        System.out.println("This is AFTER method");
    }

    @Given("Input data to get weather information of {string} with api key {string}")
    public void input_data_to_get_weather_information_of_mumbai(String cityName, String apiKey) {

        RestAssured.baseURI = "https://api.openweathermap.org/";

        weatherRequest = RestAssured.given();

        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("q", cityName);
        queryParams.put("appid", apiKey);

        weatherRequest.queryParams(queryParams);
    }

    @When("The OpenWeatherMap API is triggered")
    public void the_open_weather_map_api_is_triggered() {
        // Write code here that turns the phrase above into concrete actions
        weatherResponse = weatherRequest.get("data/2.5/weather");

    }
    @Then("Validate the response for OpenWeatherMap API for given city {string}")
    public void validate_the_response_for_open_weather_map_api(String cityName) {
        // Write code here that turns the phrase above into concrete actions
        weatherResponse.prettyPrint();
        Assert.assertEquals(200, weatherResponse.statusCode());

        Assert.assertEquals(cityName, weatherResponse.jsonPath().get("name"));
    }


    @Given("Input data to get weather for a {string} with api {string}")
    public void input_data_to_get_weather_for_a_with_api(String cityName, String apiKey) {
        RestAssured.baseURI = "https://api.openweathermap.org/";

        weatherRequest = RestAssured.given();

        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("q", cityName);
        queryParams.put("appid", apiKey);

        weatherRequest.queryParams(queryParams);
    }

    @Then("Validae the response for the given {string}")
    public void validae_the_response_for_the_given(String cityName) {
        weatherResponse.prettyPrint();
        Assert.assertEquals(200, weatherResponse.statusCode());

        Assert.assertEquals(cityName, weatherResponse.jsonPath().get("name"));
    }
}
