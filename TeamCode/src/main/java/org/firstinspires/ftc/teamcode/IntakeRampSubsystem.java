package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeRampSubsystem {
    private final DcMotor intake;
    private final DcMotorEx shooter;

    private ElapsedTime timer;

    public IntakeRampSubsystem(HardwareMap hardwareMap){
       intake = hardwareMap.get(DcMotor.class, "intake");
       intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       shooter = hardwareMap.get(DcMotorEx.class, "shooter");
       shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        shooter.setPower(-0.2);
    }

    public void shooterOff() {
        shooter.setPower(0);
    }

    public Command shooterUnjam() {
        if(shooter.getVelocity()<0.4)
            shooterReverse();
    }

    public double getShooterSpeed(){
        return shooter.getVelocity();
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

    public Command shooterShoot() {
        return Command.build()
                .setStart(() -> shooterOnCommand())
                .setExecute(() -> {
//                    getShooterSpeed();
                    if (getShooterSpeed() == 1.0) {
                        intakeRampOn();
                        timer = new ElapsedTime();
                    }
                })
                .setDone(() -> {
                    if (timer.seconds() >= 4) {
                        intakeOff();
                        shooterOff();
                        return true;
                    }
                    if (getShooterSpeed() == 0) {
                        //intakeReverse();
                       //shooterReverse();
                        timer.reset();
                        while (timer.seconds() <= 1.0) {
                            intakeReverse();
                            shooterReverse();
                        }
                        intakeOff();
                        shooterOff();
                        return true;
                    }
                    return false;

                });
    }

    //Command shooterUnjam = Command.build()
    //    .setStart(() -> pidController.setTarget(RAISED_ARM_POSITION)) *start the shooter motor*
    //    .setExecute(() -> {
    //        armMotor.setPower(pidController.calculate(armMotor.getCurrentPosition())); *check if the shooter is up to speed*
    //    }) *if motor is up to speed then use the intake wheels to put the balls up into the shooter*
    //    .setDone(() -> Math.abs(pidController.getTarget() - armMotor.getCurrentPosition()) < 10) *done when the shooter is at a constant speed*
    //    .setEnd(endCondition -> armMotor.setPower(0))
    //    .requiring(armMotor);
}
