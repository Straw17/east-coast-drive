/*░░░░░░░░░░░░░░██████████████████
░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░████
░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██
░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██
░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██
░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██████░░░░██
░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██████░░░░██
░░░░░░░░██░░░░██████░░░░██░░░░██████░░░░██
░░░░░░░░░░██░░░░░░░░░░██████░░░░░░░░░░██
░░░░░░░░████░░██░░░░░░░░░░░░░░░░░░██░░████
░░░░░░░░██░░░░██████████████████████░░░░██
░░░░░░░░██░░░░░░██░░██░░██░░██░░██░░░░░░██
░░░░░░░░░░████░░░░██████████████░░░░████
░░░░░░░░██████████░░░░░░░░░░░░░░██████████
░░░░░░██░░██████████████████████████████░░██
░░░░████░░██░░░░██░░░░░░██░░░░░░██░░░░██░░████
░░░░██░░░░░░██░░░░██████░░██████░░░░██░░░░░░██
░░██░░░░████░░██████░░░░██░░░░██████░░████░░░░██
░░██░░░░░░░░██░░░░██░░░░░░░░░░██░░░░██░░░░░░░░██
░░██░░░░░░░░░░██░░██░░░░░░░░░░██░░██░░░░░░░░░░██
░░░░██░░░░░░██░░░░████░░░░░░████░░░░██░░░░░░██
░░░░░░████░░██░░░░██░░░░░░░░░░██░░░░██░░████
░░░░░░░░██████░░░░██████████████░░░░██████
░░░░░░░░░░████░░░░██████████████░░░░████
░░░░░░░░██████████████████████████████████
░░░░░░░░████████████████░░████████████████
░░░░░░░░░░████████████░░░░░░████████████
░░░░░░██████░░░░░░░░██░░░░░░██░░░░░░░░██████
░░░░░░██░░░░░░░░░░████░░░░░░████░░░░░░░░░░██
░░░░░░░░██████████░░░░░░░░░░░░░░██████████*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class EastCoastDrive {
    CANSparkMax lFront, rFront, lMaster, rMaster, lRear, rRear;
    Solenoid shiftyBoi;
    DifferentialDrive drive;
    Joystick primary;
    public EastCoastDrive() {
        initDrive();
        primary = new Joystick(0);
    }

    public void initDrive() {
        lMaster = new CANSparkMax(RobotMap.MOTOR_LMASTER, MotorType.kBrushless);
        lFront = new CANSparkMax(RobotMap.MOTOR_LFRONT, MotorType.kBrushless);
        lRear = new CANSparkMax(RobotMap.MOTOR_LREAR, MotorType.kBrushless);
        rMaster = new CANSparkMax(RobotMap.MOTOR_RMASTER, MotorType.kBrushless);
        rFront = new CANSparkMax(RobotMap.MOTOR_RFRONT, MotorType.kBrushless);
        rRear = new CANSparkMax(RobotMap.MOTOR_RREAR, MotorType.kBrushless);

        lFront.follow(lMaster);
        lRear.follow(lMaster);
        rFront.follow(rMaster);
        rRear.follow(rMaster);

        drive = new DifferentialDrive(lMaster, rMaster);
    }
    public void setToBrake() {
        lMaster.setIdleMode(IdleMode.kBrake);
        lMaster.setIdleMode(IdleMode.kBrake);
        lRear.setIdleMode(IdleMode.kBrake);
        lFront.setIdleMode(IdleMode.kBrake);
        rRear.setIdleMode(IdleMode.kBrake);
        rFront.setIdleMode(IdleMode.kBrake);
    }

    public void drive() {
        double speedUsed = 0.50;
        if(primary.getAxis(Hand.kRight)) {
        
        }
        if(primary.getTriggerPressed()) {
            speedUsed = 1.00;
        }
        drive.curvatureDrive(speedUsed * primary.getY(Hand.kLeft), speedUsed * primary.getX(Hand.kRight), true);
    }

    public void setShift(boolean mode){
        shiftyBoi.set(mode);
    }
    public void setDir() {
        shiftyBoi.set(primary.getTrigger());
    }
}