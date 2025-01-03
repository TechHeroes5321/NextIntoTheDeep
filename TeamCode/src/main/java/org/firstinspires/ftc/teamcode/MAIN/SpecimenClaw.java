package org.firstinspires.ftc.teamcode.MAIN;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class SpecimenClaw extends Subsystem {
    // BOILERPLATE
    public static final SpecimenClaw INSTANCE = new SpecimenClaw();
    private SpecimenClaw() { }

    // USER CODE
    public Servo servo;

    public String name = "SpecimenClaw";

    public Command open() {
        return new ServoToPosition(servo, 0.6, this);
    }

    public Command close() {
        return new ServoToPosition(servo, 0, this);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
