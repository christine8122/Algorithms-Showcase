
/**
 * Models a (nearly) infinite wall with doors at every step, but only one is the working door (with the working code printed above it) that will let the agent through.  
 */
public class CodeDoorWall {

    //location of the door with the working code word
    private long workingDoor;

    //location of the position of the agent
    private long position;

    //to keep track of whether they've looped beyond the boundaries of the board.
    private int loop;

    //number of steps the agent has taken
    private long numSteps;

    //whether the hole has been found
    private boolean doorOpened;

    //constructor
    public CodeDoorWall(long workingDoor) {
        this.workingDoor = workingDoor;
        this.position = 0;
        this.loop = 0;
        this.numSteps = 0;
        this.doorOpened = false;
    }

    public long getPosition() {
        return this.position;        
    }

    public boolean checkCodeWord() {
        if (this.workingDoor == this.position && this.loop == 0) {
            this.doorOpened = true;
        }
        return this.doorOpened;
    }

    public void stepRight() {
        if (this.position == Long.MAX_VALUE) {
            this.loop += 1;
        }
        this.position += 1;
        this.numSteps += 1;
    }

    public void stepLeft() {
        if (this.position == Long.MIN_VALUE) {
            this.loop -=1;
        }
        this.position -= 1;
        this.numSteps += 1;
    }

    public long getRatio() {
        if (!this.doorOpened) {
            throw new RuntimeException("You haven't found the hole yet!");
        } 
        double distance = (double) Math.abs(this.position);
        return (long) Math.ceil(this.numSteps / distance);
    }

}