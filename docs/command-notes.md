# Intake
* Command for deploy/undeploy.
 - Bind other Commands to deploy/undeploy for ease of use.
* Command for enabling/disabling beaters on intake.
* Command for driving roller.

# Index
* Command for load/unload.
 - Add logic to check whether the index is full.
* Command for shoot.
 - Add logic to check whether flywheel is running. Inhibit shoot command if flywheel is not.

# Climb
* Command for extending/retracting climb assembly.
* Command for propogating forwards/backwards climb assembly.

# Shooter
* Command for adjusting (increase/decrease) flywheel speed.
 - Display current flywheel speed on driver station.
* Command for enabling/disabling flywheel.
* Command for adjusting (increase/decrease) hood launch angle.
* Command for enabling/disabling automatic aiming.
 - If automatic aiming is enabled, ignore manual parameters for flywheel speed and launch angle.
