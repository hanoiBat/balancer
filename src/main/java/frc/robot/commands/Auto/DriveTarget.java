package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTarget extends CommandBase{
    DriveTrain m_driveTrain;
    double initialDistance;
    double distance;
    double percentPower;

    public DriveTarget(double distance, double percentPower) {
        m_driveTrain = new DriveTrain();
        this.distance = distance;
        this.percentPower = percentPower;
        addRequirements(m_driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        initialDistance = m_driveTrain.encoderRightMeters();
        m_driveTrain.drive(percentPower, percentPower);
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setBrakeMode(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_driveTrain.encoderRightMeters() >= initialDistance + distance;
  }
}
