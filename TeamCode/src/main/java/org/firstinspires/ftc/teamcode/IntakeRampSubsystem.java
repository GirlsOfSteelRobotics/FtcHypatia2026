package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeRampSubsystem {
    private final DcMotor intake;
    private final DcMotor shooter;

    public IntakeRampSubsystem(HardwareMap hardwareMap){
       intake = hardwareMap.get(DcMotor.class, "intake");
       intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       shooter = hardwareMap.get(DcMotor.class, "shooter");
       shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intakeOn() {
        intake.setPower(-0.45);
        shooter.setPower(0.0);
    }

    public void intakeReverse() {
        intake.setPower(0.45);
        shooter.setPower(0.0);
    }

    public void intakeOff() {
        intake.setPower(0);
    }

    public double intakeSpeed(){
        return intake.getPower();
    }

    public void shooterOn() {
        shooter.setPower(1.0);
        intake.setPower(0);
    }

    public void shooterReverse() {
        intake.setPower(0);
        shooter.setPower(-1.0);
    }

    public void shooterOff() {
        shooter.setPower(0);

    }

    public double shooterSpeed(){
        return shooter.getPower();

    }

    public Command intakeRampOn() {
        return instant(() -> intakeOn());
    }

    public Command intakeRampOff() {
        return instant(() -> intakeOff());
    }

    public Command intakeRampReverse() {
        return instant(() -> intakeReverse());
    }
    public Command shooterOnCommand() {
        return instant(() -> shooterOn());
    }

    public Command shooterOffCommand() {
        return instant(() -> shooterOff());
    }

    public Command shooterReverseCommand() {
        return instant(() -> shooterReverse());
    }
}
