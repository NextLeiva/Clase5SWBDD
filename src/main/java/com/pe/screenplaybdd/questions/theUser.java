package com.pe.screenplaybdd.questions;

import com.pe.screenplaybdd.endpoints.UserEndpoints;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.RestQuestionBuilder;

public class theUser {
    public static Question<String> theValueName(String userName) {
        return new RestQuestionBuilder<String>().about("Obtener el usuario")
                .to(UserEndpoints.Obtain_User.getPath()).with(request -> request.given().log().all().contentType(ContentType.JSON)
                        .pathParam("username", userName)).returning(response -> response.then().log().all()
                        .extract().path("username"));
    }

    public static Question<String> message(String userName) {
        return new RestQuestionBuilder<String>().about("Obtener el usuario")
                .to(UserEndpoints.Obtain_User.getPath()).with(request -> request.given().log().all()
                        .pathParam("username", userName)).returning(response -> response.then().log().all()
                        .extract().path("message"));
    }
}
