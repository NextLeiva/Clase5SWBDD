package com.pe.screenplaybdd.steps;

import com.pe.screenplaybdd.model.User;
import com.pe.screenplaybdd.tasks.CreateUser;
import com.pe.screenplaybdd.tasks.DeleteUser;
import com.pe.screenplaybdd.tasks.UpdateUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;

import static net.serenitybdd.screenplay.GivenWhenThen.*;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import com.pe.screenplaybdd.questions.*;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserStepsDefinitions {
    EnvironmentVariables environmentVariables;
    private User user;

    @Given("^el (.*) con los siguientes detalles:$")
    public void el_owner_con_los_siguientes_detalles(String actor, List<Map<String, String>> listOfData) {
        theActorCalled(actor).whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("url")));
        Map<String, String> userData = listOfData.get(0);
        this.user = new User(Integer.parseInt(userData.get("id")), userData.get("username"), userData.get("firstName"), userData.get("lastName"), userData.get("email"),
                userData.get("password"), userData.get("phone"), Integer.parseInt(userData.get("userStatus")));
    }

    @When("^el owner registra el usuario$")
    public void elOwnerRegistraElUsuario() {
        theActorInTheSpotlight().attemptsTo(CreateUser.asNewUser(this.user));
    }

    @Then("^el usuario se debe visualizar en la lista con su nombre (.*)$")
    public void elUsuarioSeDebeVisualizarEnLaListaConSuNombreErick(String users) {
        String userRegistered = theActorInTheSpotlight().recall("userName");
        theActorInTheSpotlight().should(seeThat(theUser.theValueName(userRegistered), is(equalTo(users))));
    }

    @When("^el owner actualiza el usuario (.*)$")
    public void elOwnerActualizaElUsuarioErick(String userUpdate) {
        theActorInTheSpotlight().attemptsTo(UpdateUser.as(this.user, userUpdate));
    }

    @When("^el owner realiza la eliminacion del usuario$")
    public void elOwnerRealizaLaEliminacionDelUsuario() {
        theActorInTheSpotlight().attemptsTo(DeleteUser.how(this.user.getUsername()));
    }

    @Then("^el owner obtiene en la busqueda el mensaje (.*)$")
    public void elOwnerObtieneEnLaBusquedaElMensajeUserNotFound(String message) {
        String userRegistered = theActorInTheSpotlight().recall("userName");
        theActorInTheSpotlight().should(seeThat(theUser.message(userRegistered), is(equalTo(message))));
    }
}
