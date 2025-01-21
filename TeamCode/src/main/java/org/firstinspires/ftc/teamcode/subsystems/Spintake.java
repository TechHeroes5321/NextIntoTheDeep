package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Spintake extends Subsystem {
    // BOILERPLATE
    public static final Spintake INSTANCE = new Spintake();
    private Spintake() { }

    // USER CODE
    public Servo servo;

    public String name = "Spintake";
    public String state;

    public Command grab() {
        state = "GRABBING";
        return new ServoToPosition(servo, 1, this);
    }

    public Command drop() {
        state = "DROPPING";
        return new ServoToPosition(servo, 0, this);
    }

    public Command stopMoving() {
        state = "STOPPED";
        return new ServoToPosition(servo, 0.5, this);
    }

    @Override
    public void periodic() {
        //OpModeData.telemetry.addData("Spintake", state);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
