package org.firstinspires.ftc.teamcode.subsystems;

import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;

import org.jetbrains.annotations.NotNull;

public class LinearSlide extends Subsystem {
    // BOILERPLATE
    public static final LinearSlide INSTANCE = new LinearSlide();
    private LinearSlide() { }
    // USER CODE
    public MotorEx motor;
    public static double Kp = 0.005;
    public static double Ki = 0;
    public static double Kd = 0;
    public static double Kf = 0;
    public static boolean motorOn = true;
    public double targetPosition = 0;
    public double motorPower;
    public PIDFController controller = new PIDFController(new PIDCoefficients(Kp,Ki,Kd), v -> Kf);
    public String name = "LinearSlide";

    public Command resetEncoderZero() {
        return new InstantCommand(
                () -> { motor.setCurrentPosition(0); return null; }
        );
    }

    @Override
    @NotNull
    public Command getDefaultCommand() {
        return new HoldPosition(motor, controller, this);
    }

    public Command toHighBasket() {
        return new RunToPosition(motor, 3200, controller, this);
    }

    public Command toAscend() {
        return new RunToPosition(motor, 1993, controller, this);
    }

    public Command grabSpecimenFromWall() {
        return new RunToPosition(motor, 200, controller, this);
    }

    public Command prepSpecimen() {
        return new RunToPosition(motor, 1800, controller, this);
    }

    public Command scoreSpecimen() {
        return new RunToPosition(motor, 1450, controller, this);
    }

    public Command toBottom() {
        return new RunToPosition(motor, 0, controller, this);
    }

    @Override
    public void periodic() {
        OpModeData.telemetry.addData("slide pos", motor.getCurrentPosition());
        OpModeData.telemetry.addData("slide power", motorPower);
    }

    public void moveMotor() {
        if(motorOn) {
            new RunToPosition(motor, targetPosition, controller, this);
        }
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoderZero();
    }

}
