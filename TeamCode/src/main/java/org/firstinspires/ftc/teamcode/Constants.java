package org.firstinspires.ftc.teamcode;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    // Insert drivetrain config here
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {

            c.frontLeftName.set("FL");
            c.frontRightName.set("FR");
            c.backLeftName.set("BL");
            c.backRightName.set("BR");
            c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
            c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
        });

    // Insert localization config here
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("Pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-1.2383162881445697);
        c.yPodOffset.set(5.184031208669107);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    // Insert Foresignt config here
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2168610602950798);
                Controller secondaryTranslationalForward = Controller.proportional(0.08012436714477959);
                Controller primaryTranslationalLateral = Controller.proportional(0.3130828610729597);
                Controller secondaryTranslationalLateral = Controller.proportional(0.11567575143833693);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.016596166606152405));
                c.brake.set(Controller.proportionalFeedforward(0.014106741615229543));

                c.headingFeedback.set(Controller.proportional(3.0366345247075732));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.042780918814928556, 0.010025900095698415));

                c.linearBrakeCoefficients.set(Matrix.diag(0.0841398198142347, 0.03884131061273095));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0016247165301772183, 0.0030175316070663798));

                c.maxAchievableForwardVelocity.set(63.134192956260996);
                c.maxAchievableStrafeVelocity.set(53.274301093032385);
                c.naturalForwardDeceleration.set(33.186016259433444);
                c.naturalStrafeDeceleration.set(59.13816135824249);
            }
    );

    // Insert Follower create() here
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}