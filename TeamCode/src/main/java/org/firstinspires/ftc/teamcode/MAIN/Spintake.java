package org.firstinspires.ftc.teamcode.MAIN;

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

    public Command grab() {
        return new ServoToPosition(servo, 1, this);
    }

    public Command drop() {
        return new ServoToPosition(servo, 0, this);
    }

    public Command stop() {
        return new ServoToPosition(servo, 0.5, this);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
