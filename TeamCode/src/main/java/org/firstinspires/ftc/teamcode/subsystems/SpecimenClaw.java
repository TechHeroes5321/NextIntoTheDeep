package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import java.util.Objects;

public class SpecimenClaw extends Subsystem {
    // BOILERPLATE
    public static final SpecimenClaw INSTANCE = new SpecimenClaw();
    private SpecimenClaw() { }

    // USER CODE
    public Servo servo;
    public String name = "SpecimenClaw";
    public String state = "NONE";


    public Command open() {
        return new InstantCommand(
                () -> { state = "OPEN";
                    new ServoToPosition(servo, 0.6, this).invoke(); // Changed this line
                    return null; }
        );
    }

    public Command close() {
        return new InstantCommand(
                () -> { state = "CLOSED";
                    new ServoToPosition(servo, 0, this).invoke(); // Changed this line
                    return null; }
        );
    }

    public Command toggle() {
        return new InstantCommand(
                () -> {
                    if(Objects.equals(state, "OPEN")) {
                        close().invoke();
                    } else {
                        open().invoke();
                    }
                    return null; }
        );
    }

    @Override
    public void periodic() {
        OpModeData.telemetry.addData("Specimen Claw", state);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        close().invoke(); // Changed this line
    }
}