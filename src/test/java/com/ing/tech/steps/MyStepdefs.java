package com.ing.tech.steps;

import com.ing.tech.model.Location;
import com.ing.tech.model.LoginRequest;
import com.ing.tech.model.dto.AccountRequestDTO;
import com.ing.tech.model.dto.ClientRequestDTO;
import com.ing.tech.model.dto.CredentialsRequestDTO;
import com.ing.tech.model.dto.TransactionRequestDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class MyStepdefs {

    private RequestSpecification rs;
    private Response response;
    private String endpointPath;

    @Given("{string} endpoint")
    public void endpoint(String arg0) {
        String basePath = "http://localhost:8080/";
        endpointPath = basePath + arg0;
        log.info(endpointPath);
        rs = given().contentType(ContentType.JSON);
    }

    @When("Call {string} method")
    public void callMethod(String arg0) {

        callMethod(arg0, rs);
        log.info("Calling: " + arg0 + " method");
    }

    private void callMethod(String arg0, RequestSpecification rs) {
        rs = rs.when();

        switch (arg0) {
            case "GET":
                response = rs.get(endpointPath);
                break;
            case "POST":
                response = rs.post(endpointPath);
                break;
            case "PUT": {
                response = rs.put(endpointPath);
            }
            break;
            case "DELETE":
                response = rs.delete(endpointPath);
                break;
        }
    }

    @Then("Response code should be {string}")
    public void responseCodeShouldBe(String arg0) {
        assertEquals(Integer.parseInt(arg0), response.getStatusCode());
    }


    @And("{string} {string} in the request body")
    public void objectInRequestBody(String arg0, String arg1) {
        String[] splitArray = arg1.split(", ");
        switch (arg0){
            case "Client":{

                ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                        splitArray[0],
                        splitArray[1],
                        new CredentialsRequestDTO(
                                splitArray[2],
                                splitArray[3]
                        ),
                        new AccountRequestDTO(
                                splitArray[2],
                                Double.parseDouble(splitArray[4])
                        )
                );
                rs = rs.given().body(clientRequestDTO);
                break;
            }
            case "Credentials":{
                LoginRequest loginRequest = new LoginRequest(
                        splitArray[0],
                        splitArray[1],
                        new Location(
                                Double.parseDouble(splitArray[2]),
                                Double.parseDouble(splitArray[3]),
                                splitArray[4]
                        ),
                        LocalDateTime.parse(splitArray[5])
                );
                rs = rs.given().contentType(ContentType.JSON).body(loginRequest);
                break;
            }
            case "Transaction":{
                TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(
                        splitArray[0],
                        splitArray[1],
                        Double.parseDouble(splitArray[2]),
                        splitArray[3],
                        Long.parseLong(splitArray[4])
                );
                rs = rs.given().contentType(ContentType.JSON).body(transactionRequestDTO);
                break;
            }
        }
    }

    @And("Response body should contain {string}")
    public void response_body_should_contain(String arg0) {
        String[] splitArray = arg0.split(", ");

        String clientResponseDTOString = response.getBody().asString();

        for (int index = 0; index < splitArray.length; index ++)
            assertTrue(clientResponseDTOString.contains(splitArray[index]));
    }
}
