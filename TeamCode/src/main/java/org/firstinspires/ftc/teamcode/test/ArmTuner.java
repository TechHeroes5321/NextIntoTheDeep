package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
@Config
@TeleOp(name = "Arm Tuner")
public class ArmTuner extends OpMode {

    public DcMotorEx motor;
    public static double motorPower;
    public double maxCurrentDraw = 0;
    public double foundTickValue;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "HypotenuseArm");
        motor.setDirection(DcMotorEx.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        motor.setPower(motorPower);
        double currentMotorTick = motor.getCurrentPosition();
        double motorCurrentDraw = motor.getCurrent(CurrentUnit.MILLIAMPS);
        if(motorCurrentDraw > maxCurrentDraw) {
            maxCurrentDraw = motorCurrentDraw;
            foundTickValue = currentMotorTick;
        }
        telemetry.addLine("Found:");
        telemetry.addData("Current Draw",maxCurrentDraw);
        telemetry.addData("Tick Pos",foundTickValue);
        telemetry.addLine("Current State:");
        telemetry.addData("Current Draw",motorCurrentDraw);
        telemetry.addData("Tick Pos",currentMotorTick);
    }
}