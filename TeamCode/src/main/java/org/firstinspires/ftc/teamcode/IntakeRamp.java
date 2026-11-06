package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeRamp {
    private final DcMotor intake;
    private final DcMotor shooter;

    public IntakeRamp(HardwareMap hardwareMap){
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


}
