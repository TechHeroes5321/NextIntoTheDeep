package org.firstinspires.ftc.teamcode.subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

public class HypotenuseArm extends Subsystem {
    // BOILERPLATE
    public static final HypotenuseArm INSTANCE = new HypotenuseArm();
    private HypotenuseArm() { }

    // USER CODE
    public MotorEx motor;

    public double Kf;

    PIDFController controller = new PIDFController(new PIDCoefficients(0.05,0,0), v -> Kf);

    public String name = "HypotenuseArm";

    @Override
    public void periodic() {
        //OpModeData.INSTANCE.telemetry.addData("hypotenuse pos", motor.getCurrentPosition());
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
