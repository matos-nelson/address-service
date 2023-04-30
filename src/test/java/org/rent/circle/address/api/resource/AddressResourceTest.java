package org.rent.circle.address.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

@QuarkusTest
@TestHTTPEndpoint(AddressResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AddressResourceTest {

    @Test
    public void Post_WhenGivenValidAddressToSave_ShouldReturnSavedAddress() {
        // Arrange
        SaveAddressDto saveAddressDto = SaveAddressDto.builder()
            .address1("My address")
            .zipcode("90001")
            .city("Los Angeles")
            .stateCode("CA")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveAddressDto)
            .when()
            .post()
            .then()
            .statusCode(200)
            .body(is("1"));
    }

    @Test
    public void Get_WhenAddressWithGivenIdExists_ShouldReturnAddressInfo() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(200)
            .body("address1", is("4800 E Interstate 440 Road"),
                "address2", is("APT 1234"),
                "zipcode", is("90001"),
                "city", is("Los Angeles"),
                "stateCode", is("CA"));
    }

    @Test
    public void Get_WhenAddressWithGivenIdDoesNotExist_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100000")
            .then()
            .statusCode(204);
    }
}
