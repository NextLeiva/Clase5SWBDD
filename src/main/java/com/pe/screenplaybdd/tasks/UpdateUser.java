package com.pe.screenplaybdd.tasks;

import com.pe.screenplaybdd.model.User;
import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static com.pe.screenplaybdd.endpoints.UserEndpoints.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {
    private final User user;
    private String userUpdate;

    public UpdateUser(User user, String userUpdate) {
        this.user = user;
        this.userUpdate = userUpdate;
    }

    public static Performable as(User user, String userUpdate) {
        return instrumented(UpdateUser.class, user, userUpdate);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to(Update_User.getPath()).with(request -> request.given().log().all()
                .contentType(ContentType.JSON).pathParam("username", userUpdate)
                .body(user)));
        if (SerenityRest.lastResponse().statusCode() == 200) {
            SerenityRest.then().log().all();
            actor.remember("userName", user.getUsername());
        }
    }
}
