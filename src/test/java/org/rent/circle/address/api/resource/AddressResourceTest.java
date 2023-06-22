package org.rent.circle.address.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.address.dto.runtime.AddressDto;
import org.rent.circle.address.dto.runtime.SaveAddressDto;

@QuarkusTest
@TestHTTPEndpoint(AddressResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AddressResourceTest {

    @Test
    public void Post_WhenGivenValidAddressToSave_ShouldReturnSavedAddress() {
        // Arrange
        SaveAddressDto saveAddressDto = SaveAddressDto.builder()
            .street1("My address")
            .zipcode("80210")
            .city("Denver")
            .stateCode("CO")
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
            .body("street1", is("4800 E Interstate 440 Road"),
                "street2", is("APT 1234"),
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

    @Test
    public void GetAll_WhenAddressesWithGivenIdsExist_ShouldReturnAddresses() {
        // Arrange

        // Act
        List<AddressDto> result = given()
            .when()
            .get("/?ids=100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<List<AddressDto>>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("4800 E Interstate 440 Road", result.get(0).getStreet1());
        assertEquals("APT 1234", result.get(0).getStreet2());
        assertEquals("90001", result.get(0).getZipcode());
        assertEquals("Los Angeles", result.get(0).getCity());
        assertEquals("CA", result.get(0).getStateCode());
    }

    @Test
    public void GetAll_WhenAddressesWithGivenIdDoesNotExist_ShouldReturnOk() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?ids=100000")
            .then()
            .statusCode(200).body(is("[]"));
    }
}
