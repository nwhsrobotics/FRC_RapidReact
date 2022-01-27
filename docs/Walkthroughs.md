# Walkthroughs

## Table of Contents
1. Using Constants.java
2. Using the XBox Controller
3. Using the SparkMax Controller

## 1. Using Constants.java
**Overview** 

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
    
## 2. Getting Started with the Xbox Controller
**The Controller Workflow**
1. When you press a button on the controller, it will call a *Command*
2. The command will call method(s) from a *Subsystem*

**Getting Started with Xbox Controller and Commands**
1. Grab your imports in RobotContainer.java
```
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
```
2. Create your controller object in RobotContainer.java
```
public class RobotContainer {
    private final XboxController m_joy0 = new XboxController(0); //joystick #0
    private final JoystickButton joy0_a = new JoystickButton(m_joy0, 1); //button 'a' on joystick
}
```



## 3. Using the SparkMax Controller
**SparkMAX Advantages/Features**
* Sparks are a great way to control precise motor movement
* Sparks can operate under a brushless mode making them very powerful — torque!

**How To Code**
1. Create a new subsystem for your SparkMax


