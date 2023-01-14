package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants.balance;

public class AutoBalance extends CommandBase{
    private DriveTrain m_driveTrain;

    private double error;
    private double currentAngle;
    private double drivePower;

    public AutoBalance() {
      m_driveTrain = new DriveTrain();
      addRequirements(m_driveTrain);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      this.currentAngle = m_driveTrain.getPitch();
  
      error = balance.BEAM_BALANCED_GOAL_DEGREES - currentAngle;
      drivePower = -Math.min(balance.BEAM_BALANACED_DRIVE_KP * error, 1);

      if (drivePower < 0) {
        drivePower *= balance.BACKWARDS_BALANCING_EXTRA_POWER_MULTIPLIER;
      }
      if (Math.abs(drivePower) > 0.4) {
        drivePower = Math.copySign(0.4, drivePower);
      }
  
      m_driveTrain.drive(drivePower, drivePower);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      m_driveTrain.setBrakeMode(true);
    }

    @Override
    public boolean isFinished() {
      return Math.abs(error) < balance.BEAM_BALANCED_ANGLE_TRESHOLD_DEGREES;
    }
}
