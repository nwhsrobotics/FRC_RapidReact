// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final double SECONDS_PER_TICK = 1.0 / 50.0;
    
    public final class IDs {
        public final class CAN {
            // CAN ID addresses.
            public static final int DRIVE_LEFT_FRONT = 8;
            public static final int DRIVE_RIGHT_FRONT = 11;
            public static final int DRIVE_LEFT_BACK = 9;
            public static final int DRIVE_RIGHT_BACK = 10;
            public static final int INDEXER = 12; // was 10
            public static final int SHOOTER_FLYWHEEL = 15;
            public static final int SHOOTER_FLYWHEEL2 = 16;
            public static final int CLIMB_RIGHT_ARM = 20;
            public static final int CLIMB_LEFT_ARM = 21;
            public static final int CLIMB_RIGHT_SHOULDER = 22;
            public static final int CLIMB_LEFT_SHOULDER = 23;
            public static final int INTAKE_BEATER = 7;
            public static final int INTAKE_MOTOR_ARM = 4;
        }

        public final class Controls {
            public static final int JOYSTICK = 0;
        }
    }
}
