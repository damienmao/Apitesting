package testing;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

public class ResponseValidation {

    public static ResponseSpecification responseSpecification;
    public static ResponseSpecBuilder responseSpecBuilder;

    public static ResponseSpecification getGenericResponseSpec(){
        responseSpecBuilder=new ResponseSpecBuilder();
        //responseSpecBuilder.expectHeader("Content-Type","application/json;charset-UTF-8");
        //responseSpecBuilder.expectHeader("Tranfer-Encoding","chunked");
        responseSpecBuilder.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);
        responseSpecification=responseSpecBuilder.build();
        return responseSpecification;

    }
}
