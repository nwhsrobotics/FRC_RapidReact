// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */


  private NetworkTableInstance fmsNetworkInstance;
  private NetworkTable fmsNetworkTable;
  private NetworkTableEntry allianceStatus;



  private boolean m_isRedAlliance;
  
  private double m_green_centerX;
  private double m_green_centerY;
  private double m_green_dist;

  private double m_ball_centerX;
  private double m_ball_centerY;
  private double m_closestBallDist_in = -1;



  


  public VisionSubsystem() {
    //creating a new network table instance to pull FMSInfo and get our alliance
    fmsNetworkInstance = NetworkTableInstance.getDefault();
    fmsNetworkTable = fmsNetworkInstance.getTable("FMSInfo");
    allianceStatus = fmsNetworkTable.getEntry("IsRedAlliance");
    
    m_isRedAlliance = allianceStatus.getBoolean(false);
    SmartDashboard.putBoolean("isRedAlliance", m_isRedAlliance);
    SmartDashboard.putNumber("Motor Velocity", 5000);
    

    
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
    //gets the distance to the closest ball 
    
    
    
    allianceStatus = fmsNetworkTable.getEntry("IsRedAlliance");
    

    
  }

  //if the distance does not exist, return -1
  //else it will return the distance to the ball in inches
  public double getBallDist_in(){
    m_closestBallDist_in = SmartDashboard.getNumber("Ball Distance", -1);
    if (m_closestBallDist_in < 0){
      return -1;
    } else {
      return m_closestBallDist_in;
    }
  }

  public boolean isRedAlliance(){
    return m_isRedAlliance;
  }

  //@return a value between 0 and 1. If 0 item is on left side, if 1, item is on right side
  public double getGreenCenterX(){
    m_green_centerX = SmartDashboard.getNumber("Green X", -1);

    if (m_green_centerX < 0){
      return -1;
    } else {
      return m_green_centerX;
    }

  }

  //@return a value between 0 and 1. If 0 ball is on left side, if 1, ball is on right side
  public double getBallCenterX(){
    m_ball_centerX = SmartDashboard.getNumber("Ball X", -1);

    if (m_ball_centerX < 0){
      return -1;
    } else {
      return m_ball_centerX;
    }

  }

  //@return a value between 0 and 1. If 0 item is on the top, if 1, item is on the bottom
  public double getGreenCenterY(){
    m_green_centerY = SmartDashboard.getNumber("Green Y", -1);

    if (m_green_centerY < 0){
      return -1;
    } else {
      return m_green_centerY;
    }

  }

  //@return a value between 0 and 1. If 0 item is on the top, if 1, item is on the bottom
  public double getBallCenterY(){
    m_ball_centerY = SmartDashboard.getNumber("Ball Y", -1);

    if (m_ball_centerY < 0){
      return -1;
    } else {
      return m_ball_centerY;
    }

  }

  //@return a value between 0 and 1. If 0 item is on the top, if 1, item is on the bottom
  public double getGreenDist_in(){
    m_green_dist = SmartDashboard.getNumber("Green Distance", -1);

    if (m_green_dist < 0){
      return -1;
    } else {
      return m_green_dist;
    }

  }
  

  


}
