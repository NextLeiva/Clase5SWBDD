package com.pe.screenplaybdd.tasks;

import com.pe.screenplaybdd.model.User;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.pe.screenplaybdd.endpoints.UserEndpoints.*;

public class CreateUser implements Task {
    private final User user;

    public CreateUser(User user) {
        this.user = user;
    }

    public static Performable asNewUser(User user) {
        return instrumented(CreateUser.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(Create_User.getPath()).with(request -> request.given().log().all()
                .contentType(ContentType.JSON).body(user)));
        if (SerenityRest.lastResponse().statusCode() == 200) {
            SerenityRest.then().log().all();
            actor.remember("userName", user.getUsername());
        }
    }
}
