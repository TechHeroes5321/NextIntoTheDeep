package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

public class LinearSlide extends Subsystem {
    // BOILERPLATE
    public static final LinearSlide INSTANCE = new LinearSlide();
    private LinearSlide() { }

    // USER CODE
    public MotorEx motor;

    public PIDFController controller = new PIDFController(new PIDFCoefficients(0.005, 0.0, 0.0,0));

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
