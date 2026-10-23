package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(group = "Examples", name = "BiancaTeleop")
public class BiancaTeleop extends OpMode {

    private Follower follower;

    private IntakeSubsystem intake;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        follower.manual(forward, lateral, turn);
        follower.update();


        if (gamepad1.left_trigger >0.5){
            intake.on();
        }
        else{
            intake.off();
        }
    }
}
