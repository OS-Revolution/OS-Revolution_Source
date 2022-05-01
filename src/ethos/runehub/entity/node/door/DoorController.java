package ethos.runehub.entity.node.door;

import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;
import org.runehub.api.model.world.Face;
import org.runehub.api.model.world.region.location.Location;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Polygon;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class DoorController {

    public static final List<DoorDefinition> DOOR_DEFINITIONS = new ArrayList<>();

    private static DoorController instance = null;

    public static DoorController getInstance() {
        if (instance == null)
            instance = new DoorController();
        return instance;
    }

    private static final int[][] OPEN_COORD_OFFSETS = new int[][]{new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0},
            new int[]{0, -1}, new int[]{0, 1}, new int[]{1, 0}};
    private static final int[][] CLOSED_COORD_OFFSETS = new int[][]{new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0},
            new int[]{-1, 0}, new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}};

    private void rotateDoorOpen(int propId, int propX, int propY, int propZ) {
        int face = Region.getWorldObject(propId, propX, propY, propZ).get().face;
        System.out.println("Face: " + face + " / " + Face.values()[face]);
        Server.getGlobalObjects().add(new GlobalObject(
                -1,
                propX,
                propY,
                propZ,
                face,
                0
        ));

        Server.getGlobalObjects().add(new GlobalObject(
                propId - 1,
                propX + OPEN_COORD_OFFSETS[face][0],
                propY + OPEN_COORD_OFFSETS[face][1],
                propZ,
                this.getClosedDoorFace(Face.values()[face]).ordinal(),
                0
        ));
    }

    private void rotateDoorOpenForPlayer(Player player, int propId, int propX, int propY, int propZ) {
        int face = Region.getWorldObject(propId, propX, propY, propZ).get().face;
        System.out.println("Face: " + face + " / " + Face.values()[face]);
        player.getPA().checkObjectSpawn(
                -1,
                propX,
                propY,
                face,
                0
        );

        player.getPA().checkObjectSpawn(
                propId - 1,
                propX + OPEN_COORD_OFFSETS[face][0],
                propY + OPEN_COORD_OFFSETS[face][1],
                this.getClosedDoorFace(Face.values()[face]).ordinal(),
                0
        );
    }

    private void rotateDoorClosedForPlayer(Player player,int propId, int propX, int propY, int propZ) {
        int face = Region.getWorldObject(propId, propX, propY, propZ).get().face;
        System.out.println("Closed Face: " + face + " / " + Face.values()[face]);
        player.getPA().checkObjectSpawn(
                -1,
                propX,
                propY,
                face,
                0
        );

        player.getPA().checkObjectSpawn(
                propId + 1,
                propX + CLOSED_COORD_OFFSETS[face][0],
                propY + CLOSED_COORD_OFFSETS[face][1],
                this.getOpenDoorFace(Face.values()[face]).ordinal(),
                0
        );
    }

    private void rotateDoorClosed(int propId, int propX, int propY, int propZ) {
        int face = Region.getWorldObject(propId, propX, propY, propZ).get().face;
        System.out.println("Closed Face: " + face + " / " + Face.values()[face]);
        Server.getGlobalObjects().add(new GlobalObject(
                -1,
                propX,
                propY,
                propZ,
                face,
                0
        ));

        Server.getGlobalObjects().add(new GlobalObject(
                propId + 1,
                propX + CLOSED_COORD_OFFSETS[face][0],
                propY + CLOSED_COORD_OFFSETS[face][1],
                propZ,
                this.getOpenDoorFace(Face.values()[face]).ordinal(),
                0
        ));
    }

    private Face getClosedDoorFace(Face currentFace) {
        System.out.println("Current Face: " + currentFace);
        switch (currentFace) {
            case NORTH:
                return Face.SOUTHWEST;
            case NORTHEAST:
                return Face.WEST;
            case EAST:
                return Face.SOUTHEAST;
            case SOUTHEAST:
                return Face.SOUTH;
            case SOUTH:
                break;
            case SOUTHWEST:
                return Face.NORTH;
            case WEST:
                break;
            case NORTHWEST:
                return Face.NORTH;

        }
        return null;
    }

    private Face getOpenDoorFace(Face currentFace) {
        System.out.println("Current Face: " + currentFace);
        switch (currentFace) {
            case NORTH:
                return Face.SOUTHWEST;
            case NORTHEAST:
                break;
            case EAST:
                return Face.SOUTHEAST;
            case SOUTHEAST:
                return Face.EAST;
            case SOUTH:
                return Face.SOUTHEAST;
            case SOUTHWEST:
                return Face.NORTH;
            case WEST:
                return Face.NORTHEAST;
            case NORTHWEST:
                return Face.NORTH;

        }
        return null;
    }

    public void openDoorForPlayer(Player player, int objectId, int objectX, int objectY) {
        this.rotateDoorOpenForPlayer(player,objectId, objectX, objectY, player.heightLevel);
    }

    public void closeDoorForPlayer(Player player, int objectId, int objectX, int objectY) {
        this.rotateDoorClosedForPlayer(player,objectId, objectX, objectY, player.heightLevel);
    }

    public void openDoorForEveryone(Player player, int objectId, int objectX, int objectY) {
        this.rotateDoorOpen(objectId, objectX, objectY, player.heightLevel);
    }

    public void closeDoorForEveryone(Player player, int objectId, int objectX, int objectY) {
        this.rotateDoorClosed(objectId, objectX, objectY, player.heightLevel);
    }


    private Polygon create3x3AroundPoint(Location location) {
        return this.create3x3AroundPoint(location.getXCoordinate(), location.getZCoordinate());
    }

    private Polygon create3x3AroundPoint(Point point) {
        return this.create3x3AroundPoint(point.getX(), point.getY());
    }

    private Polygon create3x3AroundPoint(int x, int y) {
        return new Rectangle(
                new Point(x - 1, y - 1),
                new Point(x + 1, y + 1));
    }


    /**
     * Returns the door's face based on the player's face
     *
     * @param playerFace
     * @return
     */
    private Face getDoorFace(Face playerFace) {
        final int playerFaceIndex = playerFace.ordinal();

        if (playerFaceIndex + 4 >= Face.values().length) {
            return Face.values()[playerFaceIndex - 4];
        } else if (playerFaceIndex - 4 <= Face.values().length) {
            return Face.values()[playerFaceIndex + 4];
        } else {
            return Face.NORTH; //Handles open doors
        }
    }

    private Face getDoorFace(int index) {
        switch (index) {
            case 0:
                return Face.SOUTHWEST;
            case 1:
                return Face.SOUTH;
            case 2:
                return Face.SOUTHEAST;
            case 3:
                return Face.WEST;
            case 4: //Same location
                return Face.SOUTH;
            case 5:
                return Face.EAST;
            case 6:
                return Face.NORTHWEST;
            case 7:
                return Face.NORTH;
            case 8:
                return Face.NORTHEAST;
        }
        return null;
    }

    private boolean isBlockedNorthWest(Location location) {
        return this.isBlocked(location, Face.NORTHWEST);
    }

    private boolean isBlockedNorthEast(Location location) {
        return this.isBlocked(location, Face.NORTHEAST);
    }

    private boolean isBlockedNorth(Location location) {
        return this.isBlocked(location, Face.NORTH);
    }

    private boolean isBlockedSouthEast(Location location) {
        return this.isBlocked(location, Face.SOUTHEAST);
    }

    private boolean isBlockedSouthWest(Location location) {
        return this.isBlocked(location, Face.SOUTHWEST);
    }

    private boolean isBlockedSouth(Location location) {
        return this.isBlocked(location, Face.SOUTH);
    }

    private boolean isBlockedEast(Location location) {
        return this.isBlocked(location, Face.EAST);
    }

    private boolean isBlockedWest(Location location) {
        return this.isBlocked(location, Face.WEST);
    }

    private boolean isBlocked(Location location, Face face) {
        return this.isBlocked(location.getXCoordinate(), location.getZCoordinate(), location.getYCoordinate(), face);
    }

    private boolean isBlocked(int x, int y, int z, Face face) {
        switch (face) {
            case NORTH:
                return Region.projectileBlockedNorth(x, y, z);
            case NORTHEAST:
                return Region.projectileBlockedNorthEast(x, y, z);
            case EAST:
                return Region.projectileBlockedEast(x, y, z);
            case SOUTHEAST:
                return Region.projectileBlockedSouthEast(x, y, z);
            case SOUTH:
                return Region.projectileBlockedSouth(x, y, z);
            case SOUTHWEST:
                return Region.projectileBlockedSouthWest(x, y, z);
            case WEST:
                return Region.projectileBlockedWest(x, y, z);
            case NORTHWEST:
                return Region.projectileBlockedNorthWest(x, y, z);
            default:
                return false;
        }
    }

    private boolean pointMatchesLocation(Point point, Location location) {
        return (point.getX() == location.getXCoordinate()) && (point.getY() == location.getZCoordinate());
    }

    private Location pointToLocation(Point point) {
        return new Location(point.getX(), point.getY());
    }

    private boolean goodDistance(Location propLocation, Location playerLocation) {
        return Misc.goodDistance(propLocation.getXCoordinate(), propLocation.getYCoordinate(), playerLocation.getXCoordinate(), playerLocation.getYCoordinate(), 1);
    }

    private DoorController() {
//        DOOR_DEFINITIONS.add(
//                new DoorDefinition(
//                        11775,
//                        DoorType.PROP_ROTATE.ordinal(),
//                        DoorHingeType.LEFT.ordinal(),
//                        3118,
//                        3247,
//                        0,
//                        0
//                )
//        );
//        DoorController.DOOR_DEFINITIONS.forEach(doorDefinition -> {
//            Server.getGlobalObjects().add(new GlobalObject(
//                    doorDefinition.getObjectId(),
//                    doorDefinition.getX(),
//                    doorDefinition.getY(),
//                    doorDefinition.getZ(),
//                    doorDefinition.getFace()
//            ));
//        });
    }
}
