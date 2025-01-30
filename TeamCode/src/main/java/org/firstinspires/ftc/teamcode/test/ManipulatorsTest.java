package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Manipulators test")

public class ManipulatorsTest extends OpMode {

    /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();
    DcMotorEx HypotenuseArm;
    DcMotorEx LinearSlide;
    Servo SpecimenClaw;
    double motorpower;

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        HypotenuseArm = hardwareMap.get(DcMotorEx.class, "HypotenuseArm");
        HypotenuseArm.setDirection(DcMotorEx.Direction.FORWARD);
        SpecimenClaw = hardwareMap.get(Servo.class, "SpecimenClaw");
        LinearSlide = hardwareMap.get(DcMotorEx.class, "LinearSlide");
        LinearSlide.setDirection(DcMotorEx.Direction.REVERSE);
        HypotenuseArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Status", "Initialized");
        motorpower = 0.1;
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void init_loop() {

    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void start() {
        runtime.reset();
        SpecimenClaw.setPosition(0);
    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void loop() {
        if(gamepad1.a){
            motorpower += 0.05;
        }
        if(gamepad1.b){
            motorpower -= 0.05;
        }
        if(motorpower > 1){
            motorpower = 1;
        }
        if(motorpower < -1){
            motorpower = -1;
        }

        if(!(gamepad1.left_bumper || gamepad1.right_bumper)){
            HypotenuseArm.setPower(0);
        } else if (gamepad1.right_bumper) {
            HypotenuseArm.setPower(motorpower);
        } else {
            HypotenuseArm.setPower(-motorpower);
        }

        if(!(gamepad1.dpad_up || gamepad1.dpad_down)){
            LinearSlide.setPower(0);
        } else if (gamepad1.dpad_up) {
            LinearSlide.setPower(0.5);
        } else {
            LinearSlide.setPower(-0.5);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("hyp pos", HypotenuseArm.getCurrentPosition());
        telemetry.addData("slide pos", LinearSlide.getCurrentPosition());
        telemetry.addData("power", motorpower);
        telemetry.addData("velocity", HypotenuseArm.getVelocity());
        telemetry.update();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }
}