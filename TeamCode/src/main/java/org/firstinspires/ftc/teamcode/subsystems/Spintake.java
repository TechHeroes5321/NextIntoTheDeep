package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Spintake extends Subsystem {
    // BOILERPLATE
    public static final Spintake INSTANCE = new Spintake();
    private Spintake() { }

    // USER CODE
    public Servo servo;

    public String name = "Spintake";
    public String state = "STOPPED";

    public Command grab() {
        return new InstantCommand(
                () -> { state = "GRABBING";
                    new ServoToPosition(servo, 0, this);
                    return null; }
        );
    }

    public Command drop() {
        return new InstantCommand(
                () -> { state = "DROPPING";
                    new ServoToPosition(servo, 1, this);
                    return null; }
        );
    }

    public Command stopMoving() {
        return new InstantCommand(
                () -> { state = "STOPPED";
                    new ServoToPosition(servo, 0.5, this);
                    return null; }
        );
    }

    @Override
    public void periodic() {
        OpModeData.telemetry.addData("Spintake", state);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
