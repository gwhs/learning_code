// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Map;

public class BoreEncoder extends SubsystemBase {
  private final Encoder m_encoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X);

  private ShuffleboardTab tab = Shuffleboard.getTab("Encoder");
  private GenericEntry encoderPosition = tab.add("Encoder Position", 0).getEntry();
  private GenericEntry encoderRate =
      tab.add("Encoder Rate", 0)
          .withWidget(BuiltInWidgets.kDial)
          .withProperties(Map.of("min", -500, "max", 500))
          .getEntry();
  /** Creates a new BoreEncoder. */
  public BoreEncoder() {

    m_encoder.setSamplesToAverage(5);
    m_encoder.setDistancePerPulse(1.0 / 360.0 * 2.0 * Math.PI * 1.5);
    m_encoder.setMinRate(1.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double ticks = m_encoder.get();
    // SmartDashboard.putNumber("Encoder ticks", ticks);
    // SmartDashboard.putNumber("Encoder Rate", m_encoder.getRate());
    SmartDashboard.putNumber("Encoder Distance", m_encoder.getDistance());
    encoderPosition.setDouble(ticks);
    encoderRate.setDouble(m_encoder.getRate());
  }
}
