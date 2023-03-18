package com.pe.screenplaybdd.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.pe.screenplaybdd.endpoints.UserEndpoints.*;

public class DeleteUser implements Task {

    private String user;

    public DeleteUser(String user) {
        this.user = user;
    }

    public static Performable how(String user) {
        return instrumented(DeleteUser.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from(Delete_User.getPath()).with(request -> request.given().log().all()
                .contentType(ContentType.JSON).pathParam("username", user)));
        if (SerenityRest.lastResponse().statusCode() == 200) {
            SerenityRest.then().log().all();
        }
    }
}
