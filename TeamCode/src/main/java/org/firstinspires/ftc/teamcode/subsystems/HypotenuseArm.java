package org.firstinspires.ftc.teamcode.subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

public class HypotenuseArm extends Subsystem {
    // BOILERPLATE
    public static final HypotenuseArm INSTANCE = new HypotenuseArm();
    private HypotenuseArm() { }

    // USER CODE
    public MotorEx motor;

    public  controller = new PIDController(new PIDCoefficients(0.005, 0.0, 0.0));

    public String name = "HypotenuseArm";

    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
