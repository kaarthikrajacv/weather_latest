package org.example.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.excelData.DataSource;
import org.example.excelData.Datatable;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class WeatherStepDefinitionExcel {


    Scenario scenario;

    DataSource datatable;

    RequestSpecification weatherRequest;

    Response weatherResponse;

    String city;

    @Before
    public void initialiseData(Scenario scenario) throws Exception {

        this.scenario = scenario;
        datatable = new Datatable("src/main/resources/excelData/weather_data.xls");
        datatable.createConnection();
    }

    @Given("The input specification for the city data from excel")
    public void the_input_specification_for_the_city_data_from_excel() throws Exception {

        RestAssured.baseURI = "https://api.openweathermap.org/";

        weatherRequest = RestAssured.given();

        Map<String, Object> queryParams = new HashMap<>();

        String scenarioName = scenario.getName();
        String cityName = datatable.getDataFromExcel(scenarioName, "city");
        this.city = cityName;
        String apiKey =  datatable.getDataFromExcel(scenarioName, "api_key");

        queryParams.put("q", cityName);
        queryParams.put("appid", apiKey);

        weatherRequest.queryParams(queryParams);
    }


    @When("Weather API is triigered")
    public void weather_api_is_triigered() {
        weatherResponse = weatherRequest.get("data/2.5/weather");
    }


    @Then("Validate weather response for city")
    public void validate_weather_response_for_mumbai_city() {
        weatherResponse.prettyPrint();
        Assert.assertEquals(200, weatherResponse.statusCode());
        Assert.assertEquals(city, weatherResponse.jsonPath().get("name"));
    }

    @After
    public void updateStatusOfTestCase() throws Exception {

        datatable.updateStatus(scenario.getName(), !scenario.isFailed());
    }

}
