package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.SarahBotConstants;

@Autonomous(group = "Hypatia", name = "Hypatia Shoot and Park")
public class  HypatiaShootandPark extends OpMode {
    // Set up bot
    private Follower follower;

    // Set up paths and poses
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(83.7, 133.6, 270);

    private final Pose scorePose = poseFactory.of(84, 116.6, 270);

    private final Pose midPose = poseFactory.of(105.6, 108.1, 270);

    private final Pose parkPose = poseFactory.of(129, 47.7, 270);

    // Path methods
    private Path startToScore() {
        return line(startPose, scorePose).linear(startPose, scorePose);
    }

    private Path scoreToMid(){
        return line(scorePose, midPose).linear(scorePose, midPose);
    }

    private Path midToPark(){
        return line(midPose, parkPose).linear(midPose, parkPose);
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, startToScore()),
                // Add mechanism commands here.
                follow(follower, scoreToMid()),

                follow(follower, midToPark())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = SarahBotConstants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }

    public void stop()
    {
        OpModeStorage.autonmousEndPose = follower.pose();
    }
}
