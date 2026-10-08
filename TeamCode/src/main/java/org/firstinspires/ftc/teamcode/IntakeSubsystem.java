package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem {
    private final DcMotor intake;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intake= hardwareMap.get(DcMotor.class, "intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void on() {
        intake.setPower(1.0);
    }

    public void reverse() {
        intake.setPower(-1.0);
    }

    public void off() {
        intake.setPower(0);
    }
}
