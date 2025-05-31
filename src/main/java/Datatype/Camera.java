package Datatype;

import Services.Tween;

public class Camera {

    public WorldObject subject;
    public double worldScale = 1; // world scale is the square root of the tween value
    public Vector position = new Vector(0,0,0);
    private Tween<Vector> tween;
    public Camera() {};
    public Camera(WorldObject subject) {
        this.subject = subject;
    }

    public void update() {
        if (subject == null) {return; }
        // Vector diff = subject.position.sub(position);
        moveTo(subject.position);
    }

    public void moveTo(Vector position) {
        if (tween != null) {
            if (tween.start == position) { return; }
            tween.stop();
        }
        tween = new Tween<Vector>(this.position, position, 3, (Vector v) -> {
            // System.out.println("Lerping to " + position + " from " + this.position + "; current: " + v);
            this.position = v;
        }, Ease :: easeOutExpo);
        tween.play();
    }
}
