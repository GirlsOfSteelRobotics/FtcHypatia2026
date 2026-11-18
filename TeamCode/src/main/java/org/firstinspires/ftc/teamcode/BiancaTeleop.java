package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(group = "Examples", name = "BiancaTeleop")
public class BiancaTeleop extends OpMode {

    private Follower follower;




    private IntakeRampSubsystem intake;

    @Override
    public void init() {
        follower = SarahBotConstants.create(hardwareMap);
        intake = new IntakeRampSubsystem(hardwareMap);
    }

    @Override
    public void start() {
        follower.setPose(OpModeStorage.autonmousEndPose);
        follower.update();
    }


    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        follower.manual(forward, lateral, turn);
        follower.update();


        if (gamepad1.left_trigger > 0.5) {
            intake.intakeOn();
        } else {
            intake.intakeOff();
        }
        if (gamepad1.left_bumper) {
            intake.intakeReverse();

        }
        if (gamepad1.y) {
            intake.shooterOn();
        } else {
            intake.shooterOff();

        }
        if (gamepad1.a) {
            intake.shooterReverse();
        } else {
            intake.shooterOff();


        }

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.addData("Intake Speed", intake.intakeSpeed());
        telemetry.addData("Shooter Speed", intake.shooterSpeed());
        telemetry.update();

    }
}


