package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MAIN.HypotenuseArm;

/**This file works as a template for any OpMode you may create
 * Make a new java class in the teleop or autonomous package
 * CTRL+A and Copy and paste this code into the new file
 * Fix the package and class by right clicking and show context actions
 * If needed change @TeleOp to @autonomous
 * Change the name to how it should appear in the driver station app
 * Lastly remove @Disabled so this file appears in the driver station app
 * Android studio will automatically import FTC libraries as needed
 * Have fun coding and remember Labels on Cables :D */

@TeleOp(name = "hypotenuse test")

public class hypotenusetest extends OpMode {

    /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();
    DcMotorEx HypotenuseArm;
    double motorpower;

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        HypotenuseArm = hardwareMap.get(DcMotorEx.class, "HypotenuseArm");
        HypotenuseArm.setDirection(DcMotorEx.Direction.FORWARD);
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

        if(!(gamepad1.left_bumper || gamepad1.right_bumper)){
            HypotenuseArm.setPower(0);
        } else if (gamepad1.right_bumper) {
            HypotenuseArm.setPower(motorpower);
        } else {
            HypotenuseArm.setPower(-motorpower);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("position", HypotenuseArm.getCurrentPosition());
        telemetry.addData("velocity", HypotenuseArm.getVelocity());
        telemetry.update();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }
}