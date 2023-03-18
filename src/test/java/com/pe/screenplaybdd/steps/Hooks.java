package com.pe.screenplaybdd.steps;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

public class Hooks {

    @Before
    public void setUp() {
        OnStage.setTheStage(new Cast());
    }
}
