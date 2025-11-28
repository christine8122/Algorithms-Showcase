/**
 * Finds the working code word door in the wall.
 *
 * @author Bridget Martinez and Christine Samons
 */
public class CodeDoorFinder {
    public String getAuthors(){
        String names = "Bridget Martinez and Christine Samons";
        return names;
    }
    public long findDoor(CodeDoorWall wall) {
        int step = 1;
        boolean doorOpened = false;
        long position = wall.getPosition();

        while (!doorOpened) {
            long numOfMoves = (long) Math.pow(2, step);

            if (step % 2 == 1) {
                //odd
                for (long i = 0; i < numOfMoves; i++) {
                    wall.stepLeft();
                    position = wall.getPosition();
                    if (wall.checkCodeWord()) {
                        doorOpened = true;
                        break;
                    }
                }
            } else {
                // even
                for (long i = 0; i < numOfMoves; i++) {
                    wall.stepRight();
                    position = wall.getPosition();
                    if (wall.checkCodeWord()) {
                        doorOpened = true;
                        break;
                    }
                }
            }

            if (!doorOpened) {
                step++; // increment only if door is found
            }
        }

        return position;
    }
}
