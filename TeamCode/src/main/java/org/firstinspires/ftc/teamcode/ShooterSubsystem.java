package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterSubsystem {
    private final DcMotor shooter;

    public ShooterSubsystem(HardwareMap hardwareMap) {


        shooter = hardwareMap.get(DcMotor.class, "shooter");
    }

    public void on() {
        shooter.setPower(1.0);
    }

    public void reverse() {
        shooter.setPower(-1.0);
    }

    public void off() {
        shooter.setPower(0);
    }
}
