package org.firstinspires.ftc.teamcode.MAIN;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

@TeleOp (name = "Next Euler Drive")
public class TeleOp extends NextFTCOpMode {

    // Here is where you declare your variables and OpMode Members

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void init_loop() {

    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void start() {

    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void loop() {
    telemetry();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }

    public void telemetry() {
        telemetry.addData("Hypotenuse Arm",HypotenuseArm.motor.getCurrentPosition());
        telemetry.update();
    }
}