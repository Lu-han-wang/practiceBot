package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftFront;
    private CANSparkMax leftRear;
    private CANSparkMax rightFront;
    private CANSparkMax rightRear;
    private DifferentialDrive diffDrive;
    private double speed;
    private double rotation;
    private boolean quickTurn;


    public Drivetrain(){
        initializeMotor(leftFront);
        initializeMotor(leftRear);
        initializeMotor(rightFront);
        initializeMotor(rightRear);

        setFollowers();

        diffDrive  = new DifferentialDrive(leftFront, rightFront);
    }

    private void initializeMotor(CANSparkMax motor){
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit(40);
        motor.setOpenLoopRampRate(20);
    }
    
    private void setFollowers(){
        leftRear.follow(leftFront);
        rightRear.follow(rightFront);
    }

    public void setCurvatureDrive(double speed, double rotation, boolean quickTurn){
        this.speed = speed;
        this.rotation = rotation;
        this.quickTurn = quickTurn;
    }

    @Override
    public void periodic() {
        diffDrive.curvatureDrive(this.speed, this.rotation, this.quickTurn);
    }
}
