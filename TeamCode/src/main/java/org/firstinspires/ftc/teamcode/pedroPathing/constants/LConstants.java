package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.DriveEncoderIMUConstants;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        DriveEncoderIMUConstants.forwardTicksToInches = 0.007022803764054333;
        DriveEncoderIMUConstants.strafeTicksToInches = 1;
        DriveEncoderIMUConstants.turnTicksToInches = 1;

        DriveEncoderIMUConstants.robot_Width = 15.5;
        DriveEncoderIMUConstants.robot_Length = 10;

        DriveEncoderIMUConstants.leftFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderIMUConstants.rightFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderIMUConstants.leftRearEncoderDirection = Encoder.FORWARD;
        DriveEncoderIMUConstants.rightRearEncoderDirection = Encoder.REVERSE;

        DriveEncoderIMUConstants.IMU_HardwareMapName = "imu";
        DriveEncoderIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

    }

}




