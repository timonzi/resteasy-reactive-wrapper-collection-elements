package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.resource.ParameterResourceClientApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ParametersResourceTest {

    @Inject
    @RestClient
    ParameterResourceClientApi clientApi;


    /**
     * In case of RESTEasy Classic: {@link org.acme.converter.WrapperClassParamConverter#toString} method is called.
     * In case of RESTEasy Reactive: {@link org.acme.converter.WrapperClassParamConverter#toString} method is NOT called!
     */
    @Test
    void testSingleViaClient() {
        final var restClientResult = clientApi.wrapper(new WrapperClass<>(StatusEnum.ACTIVE));
        assertEquals("ACTIVE", restClientResult);
    }


    /**
     * In case of RESTEasy Classic: {@link org.acme.converter.WrapperClassParamConverter#toString} method
     * is called for every element of the collection.
     * <p>
     * In case of RESTEasy Reactive: {@link org.acme.converter.WrapperClassParamConverter#toString} method is NOT called at all!
     */
    @Test
    void testCollectionViaClient() {
        final var parameters = List.of(new WrapperClass<>(StatusEnum.ACTIVE), new WrapperClass<>(StatusEnum.INACTIVE));
        final var restClientResult = clientApi.wrapperList(parameters);
        assertEquals("[ACTIVE, INACTIVE]", restClientResult);
    }


    /**
     * {@link org.acme.converter.WrapperClassParamConverter#toString} method does not matter here.
     */
    @Test
    void testCollectionOfStrings() {
        final var parameters = List.of("ACTIVE", "INACTIVE");
        given()
                .queryParam("wrapperList", parameters)
                .when()
                .get("/parameters/wrapperList")
                .then()
                .statusCode(200)
                .body(is("[ACTIVE, INACTIVE]"));
    }

}
