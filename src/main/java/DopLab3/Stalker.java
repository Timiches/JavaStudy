package DopLab3;

public class Stalker {
    private String name;
    private double v0 = 5;// static
    private double m = 2; // static
    private double v;
    private double m0;
    private double m1;
    private int artifacts;


    public Stalker (String name, int carryingCapacity) {
        this.name = name;
        this.m1 = 0;
        this.m0 = carryingCapacity;
        this.v = 0;
        this.artifacts = 0;
    }

    public String GetName() {
        return this.name;
    }


    public void Accrue (){
        if ( m >= 0) {
            this.m1 =  this.m1 + m;
            this.v = Math.round( this.v0 * ( 1 / ( 1 + Math.pow(this.m1,2) / Math.pow(this.m0,2)))*100);
            this.v = this.v/100;
            this.artifacts++;
            System.out.println("Stalker "+this.name+" found an artifact and now he has "+this.artifacts+". His backpack weight " + this.m1 + " kg.");
        }
    }

    public boolean Carry(){
        if( this.v > 0.1) {
            System.out.println("Stalker "+this.name+" moving with speed "+this.v+" km/h.");
            return true;
        }
        return false;
    }
}

