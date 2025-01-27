package org.firstinspires.ftc.teamcode.subsystems;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.FeedforwardEx;
import com.ThermalEquilibrium.homeostasis.Filters.Estimators.RawValue;
import com.ThermalEquilibrium.homeostasis.Parameters.FeedforwardCoefficientsEx;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

public class LinearSlide extends Subsystem {
    // BOILERPLATE
    public static final LinearSlide INSTANCE = new LinearSlide();
    private LinearSlide() { }
    // USER CODE
    public MotorEx motor;
    public static double Kp = 0.005;
    public static double Ki = 0;
    public static double Kd = 0;
    public static double Kg = 0;
    public static boolean motorOn = true;
    public double targetPosition = 0;
    public BasicSystem controlSystem;
    public String name = "LinearSlide";

    public Command resetEncoderZero() {
        return new InstantCommand(
                () -> { motor.setCurrentPosition(0); return null; }
        );
    }

    public Command toHighBasket() {
        return new InstantCommand(
                () -> { targetPosition = 3200; return null; }
        );
    }

    public Command toAscend() {
        return new InstantCommand(
                () -> { targetPosition = 1993; return null; }
        );
    }

    public Command grabSpecimenFromWall() {
        return new InstantCommand(
                () -> { targetPosition = 200; return null; }
        );
    }

    public Command prepSpecimen() {
        return new InstantCommand(
                () -> { targetPosition = 1800; return null; }
        );
    }

    public Command scoreSpecimen() {
        return new InstantCommand(
                () -> { targetPosition = 1450; return null; }
        );
    }

    public Command toBottom() {
        return new InstantCommand(
                () -> { targetPosition = 0; return null; }
        );
    }

    @Override
    public void periodic() {
        moveMotor();
        OpModeData.telemetry.addData("slide pos", motor.getCurrentPosition());
    }

    public void moveMotor() {
        if(motorOn) {
            double motorPower = controlSystem.update(targetPosition);
            motor.setPower(motorPower);
        }
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        homeostasisInit();
    }

    public void homeostasisInit() {
        FeedforwardCoefficientsEx FFCoefficientsEx = new FeedforwardCoefficientsEx(0,0,0,Kg,0);
        PIDCoefficients pidCoefficients = new PIDCoefficients(Kp, Ki, Kd);
        BasicPID pidController = new BasicPID(pidCoefficients);
        FeedforwardEx armFeedforward = new FeedforwardEx(FFCoefficientsEx);
        RawValue noFilter = new RawValue(motor::getCurrentPosition);
        controlSystem = new BasicSystem(noFilter, pidController, armFeedforward);
    }

}
