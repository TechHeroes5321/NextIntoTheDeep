package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.constants.DriveEncoderIMUConstants;
import com.pedropathing.localization.Encoder;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.DRIVE_ENCODERS_IMU;

        FollowerConstants.leftFrontMotorName = "FrontLeft";
        FollowerConstants.leftRearMotorName = "BackLeft";
        FollowerConstants.rightFrontMotorName = "FrontRight";
        FollowerConstants.rightRearMotorName = "BackRight";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        DriveEncoderIMUConstants.forwardTicksToInches = 1;
        DriveEncoderIMUConstants.strafeTicksToInches = 1;
        DriveEncoderIMUConstants.turnTicksToInches = 1;

        DriveEncoderIMUConstants.robot_Width = 1;
        DriveEncoderIMUConstants.robot_Length = 1;

        DriveEncoderIMUConstants.leftFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderIMUConstants.rightFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderIMUConstants.leftRearEncoderDirection = Encoder.REVERSE;
        DriveEncoderIMUConstants.rightRearEncoderDirection = Encoder.FORWARD;

        DriveEncoderIMUConstants.IMU_HardwareMapName = "imu";
        DriveEncoderIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

        FollowerConstants.mass = 13;

        FollowerConstants.xMovement = 57.8741;
        FollowerConstants.yMovement = 52.295;

        FollowerConstants.forwardZeroPowerAcceleration = -41.278;
        FollowerConstants.lateralZeroPowerAcceleration = -59.7819;

        FollowerConstants.translationalPIDFCoefficients = new CustomPIDFCoefficients(0.1,0,0.01,0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients = new CustomPIDFCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients = new CustomPIDFCoefficients(2,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = false;
        FollowerConstants.secondaryHeadingPIDFCoefficients = new CustomPIDFCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients = new CustomFilteredPIDFCoefficients(0.1,0,0,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients = new CustomFilteredPIDFCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0005;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
