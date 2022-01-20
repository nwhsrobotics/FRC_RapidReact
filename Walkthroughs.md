# Walkthroughs

## Table of Contents
1. Using Constants.java

## Using Constants.java
**Overview** <br>
Constants.java is used to store information that is static and does not change throughout the lifecycle of robot operation 

**Some Information that can be stored here**
* Joystick ID's 
* CAN ID's (For the SparkMax motors)
* And Other Static Variables!

**How to Code**
1. Visit Constants.java
2. Create the class for your Subsystem
   
    ```
    public final class Constants {
        
        //YOUR CLASS BELOW (the example uses the name Storage)
        public final class Storage {
            //insert id's here
        }

    }
    ```
3. Add your own static information

   ```
    public final class Constants {
        
        //YOUR CLASS BELOW (the example uses the name Storage)
        public final class Storage {
            public static final int CANID_STORAGE_MOTOR1 = 10;
        }
    }
   ```

4. Call your Constants.java information in your Subsystem
   1. Import Constants.java
   
        ```
        import frc.robot.Constants.java;
        ```
    2. Call your value

        ```
        private int MOTOR1_ID = Constants.Storage.CANID_STORAGE_MOTOR1;
        ```
    



