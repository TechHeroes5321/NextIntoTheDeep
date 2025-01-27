package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import org.firstinspires.ftc.teamcode.customCommands.MecanumDriverControlledFixed;
import org.firstinspires.ftc.teamcode.subsystems.HypotenuseArm;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.subsystems.SpecimenClaw;
import org.firstinspires.ftc.teamcode.subsystems.Spintake;

@TeleOp(name = "Euler Teleop")

public class CompetitionTeleop extends PedroOpMode {
    public CompetitionTeleop() {
        super(HypotenuseArm.INSTANCE, LinearSlide.INSTANCE, SpecimenClaw.INSTANCE, Spintake.INSTANCE);
    }
        /**Here is where you declare your variables and OpMode Members*/
    private final ElapsedTime runtime = new ElapsedTime();

    public Command driverControlled;
    public MotorEx FrontLeft;
    public MotorEx FrontRight;
    public MotorEx BackLeft;
    public MotorEx BackRight;
    public IMU imu;
    public MotorEx[] driveMotors;
    //private AndroidTextToSpeech androidTextToSpeech;

    @Override
    public void onInit () {
        telemetry.addLine("Let's Eul this lamp");
        //androidTextToSpeech.initialize();
        //androidTextToSpeech.setLanguageAndCountry("en", "US");
        //androidTextToSpeech.speak("Let's oil this lamp");
        OpModeData.telemetry = telemetry;
        mecanumDriveInit();
        telemetry.update();
    }

    @Override
    public void onWaitForStart () {

    }

    @Override
    public void onStartButtonPressed () {
        driverControlled = new MecanumDriverControlledFixed(driveMotors, gamepadManager.getGamepad1(), false, imu);
        driverControlled.invoke();
        registerControls();
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
        FrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        driveMotors = new MotorEx[] {FrontLeft, FrontRight, BackLeft, BackRight};

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD)));
        imu.resetYaw();
    }

    private void registerControls() {
        gamepadManager.getGamepad1().getA().setPressedCommand(SpecimenClaw.INSTANCE::toggle);
        //gamepadManager.getGamepad1().getDpadUp().setPressedCommand(Spintake.INSTANCE::grab);
        //gamepadManager.getGamepad1().getDpadDown().setPressedCommand(Spintake.INSTANCE::drop);
        //gamepadManager.getGamepad1().getDpadLeft().setPressedCommand(Spintake.INSTANCE::stopMoving);
        //gamepadManager.getGamepad2().getDpadRight().setPressedCommand(LinearSlide.INSTANCE::resetEncoderZero);
        //gamepadManager.getGamepad2().getDpadRight().setPressedCommand(HypotenuseArm.INSTANCE::resetEncoderZero);
        //gamepadManager.getGamepad1().getLeftBumper().setPressedCommand(HypotenuseArm.INSTANCE::toLower);
        //gamepadManager.getGamepad1().getRightBumper().setPressedCommand(HypotenuseArm.INSTANCE::retract);
        //gamepadManager.getGamepad1().getY().setPressedCommand(HypotenuseArm.INSTANCE::score);
        //gamepadManager.getGamepad1().getX().setPressedCommand(LinearSlide.INSTANCE::toHighBasket);
        gamepadManager.getGamepad1().getLeftBumper().setPressedCommand(LinearSlide.INSTANCE::toBottom);
        gamepadManager.getGamepad1().getDpadDown().setPressedCommand(LinearSlide.INSTANCE::grabSpecimenFromWall);
        gamepadManager.getGamepad1().getRightBumper().setPressedCommand(LinearSlide.INSTANCE::prepSpecimen);
        gamepadManager.getGamepad1().getDpadUp().setPressedCommand(LinearSlide.INSTANCE::scoreSpecimen);
        gamepadManager.getGamepad1().getX().setPressedCommand(LinearSlide.INSTANCE::toHighBasket);
        //gamepadManager.getGamepad1().getDpadRight().setPressedCommand(LinearSlide.INSTANCE::toAscend);
    }



    public void telemetry () {
    telemetry.addData("Status", "Run Time: " + runtime.toString());
    telemetry.update();
    }
}