package org.property.management.address.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.property.management.address.dto.SaveAddressDto;

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
}
