package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auto.DriveTarget;
import frc.robot.commands.Auto.AutoBalance;

public class ForwardBalanceAuto extends SequentialCommandGroup {
  public ForwardBalanceAuto() {
    addCommands(new DriveTarget(1, .3));
    addCommands(new AutoBalance());
  }
}