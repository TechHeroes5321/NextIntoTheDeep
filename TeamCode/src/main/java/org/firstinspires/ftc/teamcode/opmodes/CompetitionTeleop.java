package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

@TeleOp(name = "Euler Teleop")

public class CompetitionTeleop extends PedroOpMode {

    /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();
    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void onInit() {
        telemetry.addData("Status", "Initialized");
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void onWaitForStart() {

        telemetry.addData("Status", "Initialized");
    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void onStartButtonPressed() {
       runtime.reset();
    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void onUpdate() {
    telemetry();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void onStop() {

    }

    public void telemetry() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}