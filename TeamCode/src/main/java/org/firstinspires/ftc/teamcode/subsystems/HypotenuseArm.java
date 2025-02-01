package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

import org.firstinspires.ftc.teamcode.customCommands.armFeedForward;
import org.jetbrains.annotations.NotNull;

@Config
public class HypotenuseArm extends Subsystem {
    // BOILERPLATE
    public static final HypotenuseArm INSTANCE = new HypotenuseArm();
    private HypotenuseArm() { }
    // USER CODE
    public MotorEx motor;
    public static double Kp = 0.0012;
    public static double Ki = 0;
    public static double Kd = 0;
    public static double Kcos = 0.14;
    public static boolean motorOn = true;
    public static double targetPosition;
    public double tickOffset = 204;
    public PIDFController controller = new PIDFController(new PIDCoefficients(Kp,Ki,Kd),
            v -> armFeedForward.calculate(targetPosition,tickOffset,Kcos,1120));
    public String name = "HypotenuseArm";

    public Command resetEncoderZero() {
        return new InstantCommand(
                () -> { motor.setCurrentPosition(0); return null; }
        );
    }

    public void setMotorController() {
        controller = new PIDFController(new PIDCoefficients(Kp,Ki,Kd),
                v -> armFeedForward.calculate(motor.getCurrentPosition(),tickOffset,Kcos,1120));
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        setMotorController();
    }

    @Override
    public void periodic() {
        setMotorController();
        OpModeData.telemetry.addData("arm pos", motor.getCurrentPosition());
        OpModeData.telemetry.addData("arm target variable", targetPosition);
        OpModeData.telemetry.addData("controller target", controller.getTarget());
        OpModeData.telemetry.addData("arm power", motor.getPower());
    }

    @Override
    @NotNull
    public Command getDefaultCommand() {
        return new HoldPosition(motor, controller, this);
    }

    public Command toTarget() {
        return new RunToPosition(motor, targetPosition, controller, this);
    }

    public void moveMotor(){
        if (motorOn) {
            setMotorController();
            new RunToPosition(motor, targetPosition, controller, this);
        }
    }

    public Command retract() {
        return new RunToPosition(motor, 1000, controller, this);
    }

    public Command score() {
        return new RunToPosition(motor, 250, controller, this);
    }

    public Command toLower() {
        return new SequentialGroup(
            score(),
            checkpoint_1(),
            checkpoint_2()
            );
    }

    public Command checkpoint_1() {
        return new RunToPosition(motor, 80, controller, this);
    }

    public Command checkpoint_2() {
        return new RunToPosition(motor, 26, controller, this);
    }

}