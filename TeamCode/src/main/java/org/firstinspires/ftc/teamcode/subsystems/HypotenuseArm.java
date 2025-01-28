package org.firstinspires.ftc.teamcode.subsystems;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.*;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.*;
import com.ThermalEquilibrium.homeostasis.Filters.Estimators.RawValue;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Parameters.FeedforwardCoefficientsEx;
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

@Config
public class HypotenuseArm extends Subsystem {
    // BOILERPLATE
    public static final HypotenuseArm INSTANCE = new HypotenuseArm();
    private HypotenuseArm() { }
    // USER CODE
    public MotorEx motor;
    public static double Kp = 0.0015;
    public static double Ki = 0;
    public static double Kd = 0;
    public static double Kcos = 0;
    public static boolean motorOn = true;
    public static double targetPosition;
    public double ticks_in_degree = (double) 28 /9;
    public double zeroOffset;
    FeedforwardCoefficientsEx FFCoefficientsEx;
    PIDCoefficients pidCoefficients;
    public BasicSystem controlSystem;
    public String name = "HypotenuseArm";

    public Command resetEncoderZero() {
        return new InstantCommand(
                () -> { motor.setCurrentPosition(0); return null; }
        );
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        homeostasisInit();
        retract();
    }

    @Override
    public void periodic() {
        homeostasisUpdateConstants();
        moveMotor();
        OpModeData.telemetry.addData("arm pos", motor.getCurrentPosition());
    }

    public void moveMotor(){
        if (motorOn) {
            motor.setPower(controlSystem.update(targetPosition));
        }
    }

    public Command retract() {
        return new InstantCommand(
                () -> { targetPosition = 850; return null; }
        );
    }

    public Command score() {
        return new InstantCommand(
                () -> { targetPosition = 250; return null; }
        );
    }

    public Command toLower() {
        return new InstantCommand(
                () -> { targetPosition = 26; return null; }
        );
    }

    public void homeostasisInit() {
    FFCoefficientsEx = new FeedforwardCoefficientsEx(0, 0, 0, 0, Kcos);
    pidCoefficients = new PIDCoefficients(Kp, Ki, Kd);
    BasicPID pidController = new BasicPID(pidCoefficients);
    FeedforwardEx armFeedforward = new FeedforwardEx(FFCoefficientsEx);
    RawValue noFilter = new RawValue(motor::getCurrentPosition);
    controlSystem = new BasicSystem(noFilter, pidController, armFeedforward);
    }
    Math.cos(Math.toRadians(armPos / ticks_in_degree + zeroOffset )) * Kcos;
    public void homeostasisUpdateConstants() {
        pidCoefficients.Kp = Kp;
        pidCoefficients.Ki = Ki;
        pidCoefficients.Kd = Kd;
        FFCoefficientsEx.Kcos = Kcos;
    }

}