package testing;

import io.cucumber.java.hu.Ha;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.RestAssured;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SerenityRunner.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTest {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://localhost:8080/";
    }

    @Title("testing if response time is less than 10s")
    @Test
    public void test001(){
        SerenityRest.given().when().get("coins/bitcoin").then().log().all().statusCode(200).spec(ResponseValidation.getGenericResponseSpec());
    }

    @Title("get the name of id bitcoin")
    //@Ignore
    @Test
    public void test002(){
        HashMap<String,Object> value= SerenityRest.given().when().get("coins/bitcoin").then().log().all().statusCode(200).extract().path("findAll{it.id='bitcoin'}.get(0)");

        System.out.println("the value "+value+" contains the name Bitcoin");
        assertThat(value,hasValue("Bitcoin"));

    }

    @Title("test if api returns 404 for invalid currency")
    @Test
    public void test003(){
        SerenityRest.given().when().get("coins/bitcoi").then().log().all().statusCode(500).spec(ResponseValidation.getGenericResponseSpec());
    }

    @Title("test pagination is 10")
    @Test
    public void test004() {
        SerenityRest.given().when().get("markets?currency=usd&id=bitcoin").then().assertThat().statusCode(200).body("size()", is(1));;
    }

    @Title("testing if response time is less than 10s")
    @Test
    public void test005(){
        SerenityRest.given().when().get("/markets?vs_currency=aud&id=bitcoin").then().log().all().statusCode(200).spec(ResponseValidation.responseSpecification);
    }

    @Title("get name of id bitcoin")
    @Test
    public void test006(){
        HashMap<String,Object> value=SerenityRest.given().when().get("markets?vs_currency=aud&id=bitcoin").then().log().all().statusCode(200).extract().path("findAll{it.id='bitcoin'}.get(0)");

        System.out.println("the name value of bitcoin is "+value);
        assertThat(value,hasValue("bitcoin"));
    }

}
