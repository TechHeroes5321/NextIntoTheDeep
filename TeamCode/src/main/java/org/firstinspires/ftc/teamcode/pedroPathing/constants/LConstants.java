package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.DriveEncoderIMUConstants;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        DriveEncoderIMUConstants.forwardTicksToInches = 0.01422254412;
        DriveEncoderIMUConstants.strafeTicksToInches = 0.0110684374705001;
        DriveEncoderIMUConstants.turnTicksToInches = 0.01258;

        DriveEncoderIMUConstants.robot_Width = 15.83;
        DriveEncoderIMUConstants.robot_Length = 10.39;

        DriveEncoderIMUConstants.leftFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderIMUConstants.rightFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderIMUConstants.leftRearEncoderDirection = Encoder.FORWARD;
        DriveEncoderIMUConstants.rightRearEncoderDirection = Encoder.REVERSE;

        DriveEncoderIMUConstants.IMU_HardwareMapName = "imu";
        DriveEncoderIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);

    }

}




