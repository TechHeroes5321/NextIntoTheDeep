package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.MecanumDriverControlledFixed;
import org.firstinspires.ftc.teamcode.subsystems.HypotenuseArm;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.subsystems.SpecimenClaw;
import org.firstinspires.ftc.teamcode.subsystems.Spintake;

@TeleOp(name = "Euler Teleop")

public class CompetitionTeleop extends NextFTCOpMode {
    public CompetitionTeleop() {
        super(HypotenuseArm.INSTANCE, LinearSlide.INSTANCE, SpecimenClaw.INSTANCE, Spintake.INSTANCE);
    }
        /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();

    public Command driverControlled;
    public MotorEx FrontLeft;
    public MotorEx FrontRight;
    public MotorEx BackLeft;
    public MotorEx BackRight;
    public IMU imu;
    public MotorEx[] driveMotors;

    @Override
    public void onInit () {
        telemetry.addLine("Let's Eul this lamp");
        telemetry.speak("Let's oil this lamp");
        mecanumDriveInit();
        telemetry.addLine("varooooooommmmmm! :3");
        telemetry.update();
    }

    @Override
    public void onWaitForStart () {

    }

    @Override
    public void onStartButtonPressed () {
        driverControlled = new MecanumDriverControlledFixed(driveMotors, gamepadManager.getGamepad1(), false, imu);
        driverControlled.invoke();
        runtime.reset();
    }

    @Override
    public void onUpdate () {

        telemetry();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void onStop () {

    }

    public void mecanumDriveInit () {
        FrontLeft = new MotorEx("FrontLeft");
        FrontRight = new MotorEx("FrontRight");
        BackLeft = new MotorEx("BackLeft");
        BackRight = new MotorEx("BackRight");
        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        driveMotors = new MotorEx[] {FrontLeft, FrontRight, BackLeft, BackRight};
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD)));
        imu.resetYaw();
    }

    public void telemetry () {
    telemetry.addData("Status", "Run Time: " + runtime.toString());
    telemetry.update();
    }
}