// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 * 
 * To access, import this class with:
 * import frc.robot.Constants;
 * 
 * Then use wherever the value is needed, as if it was a locally declared
 * variable (i.e. Constants.IDs.CAN.DRIVE_LEFT_FRONT).
 */
public final class Constants {
    public static final int kLeftMotor1Port = 8; //was 10
    public static final int kLeftMotor2Port = 9; //was 11
    public static final int kRightMotor1Port = 10;
    public static final int kRightMotor2Port = 11;
    
    public static final double kTicksPerMeter = 14.62;  //14.62 //(1/3544.85)*0.02.
    public static final double kEncoderDistancePerPulse = 1.0 / kTicksPerMeter; //((1/20)*0.1524*Math.PI)/4096.0
    //public static final double kEncoderDistancePerPulse = 20;
    
    public static final int[] kLeftEncoderPorts = new int[] {kLeftMotor1Port, kLeftMotor2Port};
    public static final int[] kRightEncoderPorts = new int[] {kRightMotor1Port, kRightMotor2Port};
    public static final boolean kLeftEncoderReversed = true;
    public static final boolean kRightEncoderReversed = false;

    public static final double ksVolts = 0.12763;
  public static final double kvVoltSecondsPerMeter = 0.26026;
  public static final double kaVoltSecondsSquaredPerMeter = 0.071788;
  public static final double kPDriveVel = 2.3672;
 // public static final double kTrackwidthMeters = 0.555; //TODO: add our width, was 0.555
  public static final double kTrackwidthMeters = 0.555;
  public static final double kMaxSpeedMetersPerSecond = 1.25;
  public static final double kMaxAccelerationMetersPerSecondSquared = 0.25;
  public static final double kRamseteB = 1.0;
  public static final double kRamseteZeta = 0.4;
  public static final DifferentialDriveKinematics kDriveKinematics =
  new DifferentialDriveKinematics(kTrackwidthMeters);
    public static final double SECONDS_PER_TICK = 1.0 / 50.0;
    
    public final class IDs {
        public final class CAN {
            // CAN ID addresses.
            public static final int DRIVE_LEFT_FRONT = 8;   // NOTE: these drive CAN ID's are not used
            public static final int DRIVE_RIGHT_FRONT = 11; // see : kLeftMotor1Port above
            public static final int DRIVE_LEFT_BACK = 9;    //
            public static final int DRIVE_RIGHT_BACK = 10;  //
            public static final int INDEXER = 14; // was 10 (most recent: was 7 )
            public static final int SHOOTER_FLYWHEEL = 12;
            public static final int SHOOTER_FLYWHEEL2 = 13;
            public static final int CLIMB_RIGHT_ARM = 5; //was 20
            public static final int CLIMB_LEFT_ARM = 17;
            public static final int CLIMB_RIGHT_SHOULDER = 22;
            public static final int CLIMB_LEFT_SHOULDER = 23;
            public static final int INTAKE_BEATER = 16; //was 12
            public static final int INTAKE_MOTOR_ARM = 7; //was 4
        }

        public final class Controls {
            public static final int JOYSTICK = 0;
        }
    }
}
