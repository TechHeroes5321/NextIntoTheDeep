package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "hypotenuse test")

public class hypotenusetest extends OpMode {

    /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();
    DcMotorEx HypotenuseArm;
    DcMotorEx LinearSlide;
    double motorpower;

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        HypotenuseArm = hardwareMap.get(DcMotorEx.class, "HypotenuseArm");
        HypotenuseArm.setDirection(DcMotorEx.Direction.FORWARD);
        LinearSlide = hardwareMap.get(DcMotorEx.class, "LinearSlide");
        LinearSlide.setDirection(DcMotorEx.Direction.FORWARD);
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
        telemetry.addData("position", HypotenuseArm.getCurrentPosition());
        telemetry.addData("power", motorpower);
        telemetry.addData("velocity", HypotenuseArm.getVelocity());
        telemetry.update();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }
}