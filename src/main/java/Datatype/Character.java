package Datatype;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import Services.Run;
import Util.ProjectileMath;

public class Character extends WorldObject {


    public boolean[] pressedKeys = new boolean[256];

    public Callback<KeyEvent> processkeyboardInputBegan;
    public Callback<KeyEvent> processkeyboardInputEnded;

    private Services.Tween<Number> viewTween;

    public Vector moveDirection = new Vector(0,0,0);
    public double walkSpeed = 10;
    private Vector jumpImpulse = new Vector(0,1000000,0);
    private Run session;

    public Character() {
        super();
        density = 100000;
    }

    public void newMoveDirection() {
        Vector v = Vector.zero;

        if (pressedKeys['w']) {
            v = v.add(new Vector(0,0,1));
        }
        if (pressedKeys['s']) {
            v = v.add(new Vector(0,0,-1));
        }
        if (pressedKeys['a']) {
            v = v.add(new Vector(-1,0,0));
        }
        if (pressedKeys['d']) {
            v = v.add(new Vector(1,0,0));
        }
        moveDirection = v.unit();
    }

    protected void reset() {
        if (session == null) {return; }
        position = session.world.spawnPosition;
        velocity = Vector.zero;
    }

    public double getJumpHeight() {
        if (session == null) { return 0; }
        return ProjectileMath.getFinalHeightFromInitialSpeed(jumpImpulse.y, session.world.gravity.y);
    }

    public void setJumpHeight(double jumpHeight) {
        if (session == null) { throw new NullPointerException(); }
        jumpImpulse = new Vector(
            0,
            ProjectileMath.getSpeedForFinalHeight(jumpHeight,session.world.gravity.y),
            0
        );
    }

    public void connectToGame(Run game) {
        session = game;
        game.userInputListener.keyboardInputBegan.addListener((KeyEvent e) -> {
            char key = e.getKeyChar();
            pressedKeys[key] = true;
    
            if (
                   key == 'w'
                || key == 'a'
                || key == 's'
                || key == 'd'
            ) {
                newMoveDirection();
            }

            if (key == 'r') {
                reset();
            } else if (key == ' ') { // space bar = jump
                velocity = new Vector(velocity.x,5,velocity.z);
                position = position.add(new Vector(0,0.1,0));
            }
        });
        game.userInputListener.keyboardInputEnded.addListener((KeyEvent e) -> {
            char key = e.getKeyChar();
            pressedKeys[key] = false;
    
            if (
                   key == 'w'
                || key == 'a'
                || key == 's'
                || key == 'd'
            ) {
                newMoveDirection();
            }
        });


        // camera zooming
        game.userInputListener.mouseWheelMoved.addListener( (MouseWheelEvent e) -> {
            e.getClickCount();
            Number oldScale = new Number(game.world.camera.worldScale * game.world.camera.worldScale);
            Number newScale = new Number(
                Math.max(
                    Math.min(
                        (viewTween != null ? viewTween.end.v : game.world.camera.worldScale * game.world.camera.worldScale) - e.getPreciseWheelRotation() * 0.9,  19
                    ), 0.1
                )
            );

            if (viewTween != null) {
                viewTween.stop();
            }
            viewTween = new Services.Tween<Number>(oldScale, newScale, 2, (Number n) -> {
                game.world.camera.worldScale = Math.sqrt(n.v);
            }, Ease :: easeOutExpo);
            viewTween.play();

        });

        game.PhysicsStepped.addListener( (Double dt) -> {
            if (!moveDirection.equals(Vector.zero)) {
                Vector newVelocity = velocity.add(moveDirection.mul(dt * 60));
                    velocity = new Vector(
                        newVelocity.x == 0 ? 0 : (Math.abs(newVelocity.x) / newVelocity.x) * Math.min(walkSpeed, Math.abs(newVelocity.x)),
                        newVelocity.y,
                        newVelocity.z
                    );
            } else {
                velocity = velocity.sub(velocity.mul(dt * 10).mul(new Vector(1,0,1)));
            }
            physicsActive = true;
        });
    }
}
