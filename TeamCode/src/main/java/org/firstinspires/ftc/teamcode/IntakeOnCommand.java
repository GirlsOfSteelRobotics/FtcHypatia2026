package org.firstinspires.ftc.teamcode;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.ConflictBehavior;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;

import java.util.Collections;
import java.util.Set;

public class IntakeOnCommand implements Command {

    private final IntakeSubsystem intake;
    public IntakeOnCommand(IntakeSubsystem intake) {
        this.intake = intake;
    }

    @Override
    public Set<Object> requirements() {
        return Collections.emptySet();
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public InterruptedBehavior interruptedBehavior() {
        return null;
    }

    @Override
    public ConflictBehavior conflictBehavior() {
        return null;
    }

    @Override
    public BlockedBehavior blockedBehavior() {
        return null;
    }

    @Override
    public void start() {
        intake.on();
    }

    @Override
    public boolean done() {
        return true;
    }

    @Override
    public void execute() {
        //nothing here :D
    }

    @Override
    public void end(EndCondition endCondition) {
        //nothing here :D
    }
}

