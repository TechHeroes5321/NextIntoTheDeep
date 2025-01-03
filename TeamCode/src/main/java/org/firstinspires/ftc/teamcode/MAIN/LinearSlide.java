package org.firstinspires.ftc.teamcode.MAIN;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

import java.util.Collections;
import java.util.HashSet;

public class LinearSlide extends Subsystem {
    // BOILERPLATE
    public static final LinearSlide INSTANCE = new LinearSlide();
    private LinearSlide() { }

    // USER CODE
    public MotorEx motor;

    public PIDController controller = new PIDController(new PIDCoefficients(0.005, 0.0, 0.0));

    public String name = "LinearSlide";

    public Command toTop() {
        return new RunToPosition(motor, 9800.0, controller, this);
    }

    public Command prepSpecimen() {
        return new RunToPosition(motor, 1200.0, controller, this);
    }

    public Command scoreSpecimen() {
        return new RunToPosition(motor, 1200.0, controller, this);
    }

    public Command toBottom() {
        return new RunToPosition(motor, 0, controller, this);
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
