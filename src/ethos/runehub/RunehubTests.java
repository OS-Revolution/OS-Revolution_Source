package ethos.runehub;

import ethos.scaperune.entity.node.impl.RenewableNode;
import ethos.scaperune.skill.gathering.GatheringNode;

public class RunehubTests {

    enum ShipMask {
        ID, SEAFARING, MORALE, COMBAT, SPEED, VOYAGE;

        public int getOffset(int bitsPerField) {
            return (int) (Math.pow(2, bitsPerField) * this.ordinal());
        }
    }

//    public static Ship fromBitArray(long bits) {
//        int id = (int) (bits >> 54) & 0x1FF;
//        int seafaring = (int) (bits >> 45) & 0x1FF;
//        int morale = (int) (bits >> 36) & 0x1FF;
//        int combat = (int) (bits >> 27) & 0x1FF;
//        int speed = (int) (bits >> 18) & 0x1FF;
//        int voyage = (int) (bits >> 9) & 0x1FF;
//        Ship ship = new Ship(id,seafaring,morale,combat,speed,voyage);
//        return ship;
//    }

    private static void createWoodcuttingNodes() {
        GatheringNode tree1 = new GatheringNode(1278,1342,67,-1,
                8,25,1,
                28,165,-1L);

    }

    public static void main(String[] args) {
createWoodcuttingNodes();
//        Ship ship = new Ship(2, 15, 15, 10, 15, 15);
//        long shipBits = ship.toBitArray();
//        System.out.println(ship.getSeafaring());
//        System.out.println(shipBits);
//        System.out.println(fromBitArray(shipBits).getSeafaring());
    }

//    public static void main(String[] args) {
//        byte[] ship = new byte[ShipMask.values().length * 10 + 1]; // initialize array with all 0's
//        long shipLong = 0L;
//
////        setBits(ship, ShipMask.VOYAGE, 45);
////
////        System.out.println(getBits(ship, ShipMask.VOYAGE) == 45);
////        System.out.println(getValueAtIndex(ship,ShipMask.VOYAGE.getOffset()));
//
//        int maxValue = 999;
//        int bitsPerField = (int) Math.ceil(Math.log(maxValue + 1) / Math.log(2));
//
//        shipLong = setDynamicBits(shipLong,ShipMask.ID.getOffset(bitsPerField), 2);
//        shipLong = setDynamicBits(shipLong,ShipMask.SEAFARING.getOffset(bitsPerField), 15);
//        shipLong = setDynamicBits(shipLong,ShipMask.MORALE.getOffset(bitsPerField), 15);
//        shipLong = setDynamicBits(shipLong,ShipMask.COMBAT.getOffset(bitsPerField), 10);
//        shipLong = setDynamicBits(shipLong,ShipMask.SPEED.getOffset(bitsPerField), 15);
//        shipLong = setDynamicBits(shipLong,ShipMask.VOYAGE.getOffset(bitsPerField), 15);
//
//        System.out.println(getDynamicBits(shipLong, ShipMask.SEAFARING.getOffset(bitsPerField)));
//
//    }

    public static long setDynamicBits(long original, int offset, int value) {
        long mask = (1L << offset) - 1; // create a mask of maxBits 1's
        long shiftedValue = (long) value << offset; // shift the value to the correct position
        long maskedOriginal = original & ~mask; // clear the bits that will be replaced
        long result = maskedOriginal | (shiftedValue & mask); // set the new value
        return result;
    }

    public static int getDynamicBits(long original, int offset) {
        long mask = (1L << offset) - 1; // create a mask of maxBits 1's
        long shiftedValue = (original >> offset) & mask; // shift the value to the correct position and apply the mask
        int result = (int) shiftedValue; // cast to int
        return result;
    }

//    private static long setBits(long bits, int offset, int val) {
//        bits &= ~(1023L << offset); // clear the bits at the offset
//        bits |= ((long)val & 1023L) << offset; // set the bits at the offset to the desired value
//        return bits;
//    }
//
//
//    private static int getBits(long bits, int offset) {
//        return (int)((bits >> offset) & 1023L); // retrieve the value at the offset
//    }
//
//    public static int getValueAtIndex(byte[] bytes, int index) {
//        int value = ((bytes[index] & 0xFF) << 16) | ((bytes[index + 1] & 0xFF) << 8) | (bytes[index + 2] & 0xFF);
//        return value;
//    }
//
//    private static int getBits(byte[] bits, ShipMask mask) {
//        int offset = mask.getOffset();
//        int val = ((bits[offset] & 0xFF) << 16) | ((bits[offset + 1] & 0xFF) << 8) | (bits[offset + 2] & 0xFF);
//        return val;
//    }
//
//    private static void setBits(byte[] bits, ShipMask mask, int val) {
//        if (val < 0 || val > 999) {
//            throw new IllegalArgumentException("Value must be between 0 and 999");
//        }
//        int offset = mask.getOffset();
//        bits[offset] = (byte) ((val >> 16) & 0xFF);
//        bits[offset + 1] = (byte) ((val >> 8) & 0xFF);
//        bits[offset + 2] = (byte) (val & 0xFF);
//    }
}
