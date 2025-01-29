package org.firstinspires.ftc.teamcode.customCommands;

public class armFeedForward {

    public static double calculate(double currentPos, double tickOffset, double Kcos, double ticksPerRevolution) {
    return Math.toRadians((currentPos - tickOffset) * (360 / ticksPerRevolution)) * Kcos;
    }

}
