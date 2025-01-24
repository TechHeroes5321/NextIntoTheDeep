package org.firstinspires.ftc.teamcode.subsystems;

import com.ThermalEquilibrium.homeostasis.Filters.Estimators.RawValue;
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.acmerobotics.dashboard.config.Config;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.NullCommand;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleSupplier;

@Config
public class HypotenuseArm extends Subsystem {
    // BOILERPLATE
    public static final HypotenuseArm INSTANCE = new HypotenuseArm();
    private HypotenuseArm() { }

    // USER CODE
    public MotorEx motor;
    public static double Kf = 0;
    public double P = 0;
    public double I = 0;
    public double D = 0;

    //public PIDFController controller = new PIDFController(new PIDCoefficients(0.005,0,0));

    public String name = "HypotenuseArm";

    public Command resetEncoderZero() {
        motor.setCurrentPosition(0);
        return new NullCommand();
    }

    public Command toLower() {
        return new RunToPosition(motor, 0, controller, this);
    }

    public Command score() {
        return new RunToPosition(motor, 350, controller, this);
    }

    public Command retract() {
        return new RunToPosition(motor, 860, controller, this);
    }

    @Override
    public void periodic() {
        OpModeData.telemetry.addData("hypotenuse pos", motor.getCurrentPosition());
    }

    @Override
    @NotNull
    public Command getDefaultCommand() {
        return new HoldPosition(motor, controller, this);
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }


    PIDCoefficients coefficients = new PIDCoefficients(0.3,0.04,0.01);
    DoubleSupplier motorPosition = new DoubleSupplier() {
        @Override
        public double getAsDouble() {
            return exampleMotor.getPosition();
        }
    };
    BasicPID controller = new BasicPID(coefficients);
    NoFeedforward feedforward = new NoFeedforward();
    RawValue noFilter = new RawValue(motorPosition);
    BasicSystem system = new BasicSystem(noFilter,controller,feedforward);

while (true) {
        double command = system.update(referencePosition);
    }




}
