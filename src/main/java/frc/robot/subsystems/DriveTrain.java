package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants.ids;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants.wheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class DriveTrain extends SubsystemBase{
    private CANSparkMax leftPrimary = new CANSparkMax(ids.ID_LEFT_PRIMARY, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax leftSecondary = new CANSparkMax(ids.ID_LEFT_SECONDARY, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rightPrimary = new CANSparkMax(ids.ID_RIGHT_PRIMARY, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rightSecondary = new CANSparkMax(ids.ID_RIGHT_SECONDARY, CANSparkMaxLowLevel.MotorType.kBrushless);

    private MotorControllerGroup groupLeft = new MotorControllerGroup(leftPrimary, leftSecondary);
    private MotorControllerGroup groupRight = new MotorControllerGroup(rightPrimary, rightSecondary);

    private DifferentialDrive driveTrain = new DifferentialDrive(groupLeft, groupRight);

    private RelativeEncoder leftEncoder = leftPrimary.getEncoder();
    private RelativeEncoder rightEncoder = rightPrimary.getEncoder();

    AHRS navx = new AHRS(SerialPort.Port.kMXP);

    public DriveTrain(){
        
    }

    //using tank drive
    public void drive(double leftSpeed, double rightSpeed){
        driveTrain.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop(){
        driveTrain.tankDrive(0, 0);
    }

    public double getLeftEncoder(){
        return leftEncoder.getPosition();
    }

    public double getRightEncoder(){
        return rightEncoder.getPosition();
    }

    public void resetEncoders(){
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public double getAngle(){
        return navx.getAngle();
    }

    public void resetAngle(){
        navx.reset();
    }

    public double getPitch(){
        return navx.getPitch();
    }

    public double getRoll(){
        return navx.getRoll();
    }

    public double getYaw(){
        return navx.getYaw();
    }

    public void turnRight(double speed){
        driveTrain.tankDrive(speed, -speed);
    }

    public void turnLeft(double speed){
        driveTrain.tankDrive(-speed, speed);
    }

    public void setBrakeMode(boolean brake){
        if(brake){
            leftPrimary.setIdleMode(IdleMode.kBrake);
            leftSecondary.setIdleMode(IdleMode.kBrake);
            rightPrimary.setIdleMode(IdleMode.kBrake);
            rightSecondary.setIdleMode(IdleMode.kBrake);
        }else{
            leftPrimary.setIdleMode(IdleMode.kCoast);
            leftSecondary.setIdleMode(IdleMode.kCoast);
            rightPrimary.setIdleMode(IdleMode.kCoast);
            rightSecondary.setIdleMode(IdleMode.kCoast);
        }
    }

    public double encoderRightMeters() {
        double wheelCirc = (2 * Math.PI * wheel.WHEEL_RADIUS);
        double rotationsPerInch = wheelCirc / wheel.GEARRATIO;
        return Units.inchesToMeters(rotationsPerInch * rightEncoder.getPosition());
    }
}
