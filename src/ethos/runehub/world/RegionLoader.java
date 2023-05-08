package ethos.runehub.world;

import ethos.clip.ByteStream;
import ethos.clip.ObjectDef;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.util.Misc;
import org.runehub.api.io.data.DataAcessObjectBase;
import org.runehub.api.io.load.LazyLoader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class RegionLoader extends LazyLoader<Integer, Chunk> {

//    private static RegionLoader instance = null;
//
//    public static RegionLoader getInstance() {
//        if (instance == null)
//            instance = new RegionLoader();
//        return instance;
//    }

    @Override
    protected Chunk load(Integer regionId) {
        Chunk chunk = new Chunk(regionId);
        try {
            File f = new File("./Data/world/map_index.dat");
            byte[] buffer = new byte[(int) f.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            dis.readFully(buffer);
            dis.close();
            ByteStream in = new ByteStream(buffer);
            int size = in.readUnsignedWord();
            int groundFileId = 0;
            int mapObjectId = 0;
//            for (int i = 0; i < size; i++) {
                while (regionId != in.readUnsignedWord()) {

                    groundFileId = in.readUnsignedWord();
                    mapObjectId = in.readUnsignedWord();
//                    break;
                }
//            }
            System.out.println("Loading Region: " + regionId);
            System.out.println("ground: " + groundFileId);
            System.out.println("map: " + mapObjectId);
            byte[] file1 = Region.getBuffer(new File("./Data/world/map/" + mapObjectId + ".gz"));
            byte[] file2 = Region.getBuffer(new File("./Data/world/map/" + groundFileId + ".gz"));

            try {
                loadMap(chunk, new ByteStream(file1), new ByteStream(file2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chunk;
    }

    private void loadMap(Chunk chunk,ByteStream str1, ByteStream str2) {
        int[][][] tiles = new int[4][64][64];
        this.doSomething(tiles,str2);
        this.loadTileClipping(chunk,tiles);
        this.loadProps(chunk,tiles,str1);
        System.out.println("Chunk Loaded\n" + chunk.toString());
    }

    private void loadTileClipping(Chunk chunk, int[][][] tiles) {
        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    if ((tiles[z][x][y] & 1) == 1) {
                        int height = z;
                        if ((tiles[1][x][y] & 2) == 2) {
                            height--;
                        }
                        if (height >= 0 && height <= 3) {
                            chunk.setTileClipped( x, y, height,true);
                        }
                    }
                }
            }
        }
    }

    private void doSomething(int[][][] tiles, ByteStream bytes) {
        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    while (true) {
                        int v = bytes.getUByte();
                        if (v == 0) {
                            break;
                        } else if (v == 1) {
                            bytes.skip(1);
                            break;
                        } else if (v <= 49) {
                            bytes.skip(1);
                        } else if (v <= 81) {
                            tiles[z][x][y] = v - 49;
                        }
                    }
                }
            }
        }
    }

    private void loadProps(Chunk chunk,int[][][] tiles, ByteStream bytes) {
        int objectId = -1;
        int incr;
        while ((incr = bytes.getUSmart()) != 0) {
            objectId += incr;
            int location = 0;
            int incr2;
            while ((incr2 = bytes.getUSmart()) != 0) {
                location += incr2 - 1;
                int localX = (location >> 6 & 0x3f);
                int localY = (location & 0x3f);
                int height = location >> 12;
                int objectData = bytes.getUByte();
                int type = objectData >> 2;
                int direction = objectData & 0x3;
                if ((tiles[1][localX][localY] & 2) == 2) {
                    height--;
                }
                if (height >= 0 && height <= 3) {

                    chunk.addProp(new Prop(objectId,localX,localY,height,type,direction));
//                    System.out.println("Adding "+objectId+" at "+ absX + localX);
//                    addObject(region, objectId, absX + localX, absY + localY, height, type, direction);
//                    addWorldObject(region,objectId, absX + localX, absY + localY, height, direction);
                }
            }
        }
    }

    private void loadMaps(Chunk chunk, ByteStream str1, ByteStream str2) {
        int absX = (chunk.getId() >> 8) * 64;
        int absY = (chunk.getId() & 0xff) * 64;
        int[][][] someArray = new int[4][64][64];
        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    while (true) {
                        int v = str2.getUByte();
                        if (v == 0) {
                            break;
                        } else if (v == 1) {
                            str2.skip(1);
                            break;
                        } else if (v <= 49) {
                            str2.skip(1);
                        } else if (v <= 81) {
                            someArray[z][x][y] = v - 49;
                        }
                    }
                }
            }
        }
        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    if ((someArray[z][x][y] & 1) == 1) {
                        int height = z;
                        if ((someArray[1][x][y] & 2) == 2) {
                            height--;
                        }
                        if (height >= 0 && height <= 3) {
                            chunk.setTileClipped( absX + x, absY + y, height,true);
//                            addClipping(region, absX + i2, absY + i3, height, 0x200000);
                        }
                    }
                }
            }
        }
        int objectId = -1;
        int incr;
        while ((incr = str1.getUSmart()) != 0) {
            objectId += incr;
            int location = 0;
            int incr2;
            while ((incr2 = str1.getUSmart()) != 0) {
                location += incr2 - 1;
                int localX = (location >> 6 & 0x3f);
                int localY = (location & 0x3f);
                int height = location >> 12;
                int objectData = str1.getUByte();
                int type = objectData >> 2;
                int direction = objectData & 0x3;
//                if (localX < 0 || localX >= 64 || localY < 0 || localY >= 64) {
//                    continue;
//                }
                if ((someArray[1][localX][localY] & 2) == 2) {
                    height--;
                }
                if (height >= 0 && height <= 3) {
//                    System.out.println("Adding "+objectId+" at "+ absX + localX);
//                    addObject(region, objectId, absX + localX, absY + localY, height, type, direction);
//                    addWorldObject(region,objectId, absX + localX, absY + localY, height, direction);
                }
            }
        }
    }

//    private void addClip(Region region,int x, int y, int height, int shift) {
//        int regionAbsX = (region.getId() >> 8) * 64;
//        int regionAbsY = (region.getId() & 0xff) * 64;
//        int xVal = (x - regionAbsX);
//        int yVal = (y - regionAbsY);
//        if (region.clips[height] == null) {
//            region.clips[height] = new int[64][64];
//        }
//        region.clips[height][xVal][yVal] |= shift;
//    }
//
//    private void addWorldObject(Region region,int id, int x, int y, int height, int face) {
//        int regionId = region.getId();
//        if (Region.getWorldObjects().containsKey(regionId)) {
//            if (Region.objectExists(regionId, id, x, y, height)) {
//                return;
//            }
////            System.out.println("Adding existing World Object: " + id);
//            Region.getWorldObjects().get(regionId).add(new WorldObject(id, x, y, height, face));
//        } else {
////            System.out.println("Adding New World Object: " + id);
//            ArrayList<WorldObject> object = new ArrayList<>(1);
//            object.add(new WorldObject(id, x, y, height, face));
//            Region.getWorldObjects().put(regionId, object);
//        }
//    }
//
//    private void addObject(Region region, int objectId, int x, int y, int height, int type, int direction) {
//        ObjectDef def = ObjectDef.getObjectDef(objectId);
//        if (def == null) {
//            return;
//        }
//        int xLength;
//        int yLength;
//        if (direction != 1 && direction != 3) {
//            xLength = def.xLength();
//            yLength = def.yLength();
//        } else {
//            xLength = def.yLength();
//            yLength = def.xLength();
//        }
////        System.out.println("Adding New Object: " + objectId + " type: " + type);
//        if (type == 22) {
//            if (def.hasActions() && def.aBoolean767()) {
//                addClipping(region, x, y, height, 0x200000);
//                if (def.aBoolean757) {
//                    addProjectileClipping(region, x, y, height, 0x200000);
//                }
//            }
//        } else if (type >= 9) {
//            if (def.aBoolean767()) {
//                addClippingForSolidObject(region, x, y, height, xLength, yLength, def.solid());
//                if (def.aBoolean757) {
//                    addProjectileClippingForSolidObject(region, x, y, height, xLength, yLength, true);
//                }
//            }
//        } else if (type >= 0 && type <= 3) {
//            if (def.aBoolean767()) {
//                addClippingForVariableObject(region, x, y, height, type, direction, def.solid());
//                if (def.aBoolean757) {
//                    setProjectileClippingForVariableObject(region, x, y, height, type, direction, def.solid(), false);
//                }
//            }
//        }
//    }
//
//    private void addClipping(Region region, int x, int y, int height, int shift) {
//        addClip(region,x, y, height, shift);
//    }
//
//    private void addProjectileClipping(Region region, int x, int y, int height, int shift) {
//        if (region != null) {
//            if (shift > 0) {
//                region.addProjectileClip(x, y, height, shift);
//            } else {
//                region.removeProjectileClip(x, y, height, shift);
//            }
//        }
//    }
//
//    private void addProjectileClippingForSolidObject(Region region, int x, int y, int height,
//                                                     int xLength, int yLength, boolean flag) {
//        int clipping = 256;
//        if (flag) {
//            clipping += 0x20000;
//        }
//        for (int i = x; i < x + xLength; i++) {
//            for (int i2 = y; i2 < y + yLength; i2++) {
//                addProjectileClipping(region, i, i2, height, clipping);
//            }
//        }
//    }
//
//    private void addClippingForSolidObject(Region region, int x, int y, int height, int xLength, int yLength, boolean flag) {
//        int clipping = 256;
//        if (flag) {
//            clipping += 0x20000;
//        }
//        for (int i = x; i < x + xLength; i++) {
//            for (int i2 = y; i2 < y + yLength; i2++) {
//                addClipping(region, i, i2, height, clipping);
//            }
//        }
//    }
//
//    private void setProjectileClippingForVariableObject(Region region, int x, int y, int height,
//                                                        int type, int direction, boolean flag, boolean negative) {
//        if (type == 0) {
//            if (direction == 0) {
//                addProjectileClipping(region, x, y, height, negative ? -128 : 128);
//                addProjectileClipping(region, x - 1, y, height, negative ? -8 : 8);
//            } else if (direction == 1) {
//                addProjectileClipping(region, x, y, height, negative ? -2 : 2);
//                addProjectileClipping(region, x, y + 1, height, negative ? -32 : 32);
//            } else if (direction == 2) {
//                addProjectileClipping(region, x, y, height, negative ? -8 : 8);
//                addProjectileClipping(region, x + 1, y, height, negative ? -128 : 128);
//            } else if (direction == 3) {
//                addProjectileClipping(region, x, y, height, negative ? -32 : 32);
//                addProjectileClipping(region, x, y - 1, height, negative ? -2 : 2);
//            }
//        } else if (type == 1 || type == 3) {
//            if (direction == 0) {
//                addProjectileClipping(region, x, y, height, negative ? -1 : 1);
//                addProjectileClipping(region, x - 1, y + 1, height, negative ? -16 : 16);//wrong method217(16, x - 1, y + 1);
//            } else if (direction == 1) {
//                addProjectileClipping(region, x, y, height, negative ? -4 : 4);
//                addProjectileClipping(region, x + 1, y + 1, height, negative ? -64 : 64);
//            } else if (direction == 2) {
//                addProjectileClipping(region, x, y, height, negative ? -16 : 16);
//                addProjectileClipping(region, x + 1, y - 1, height, negative ? -1 : 1);
//            } else if (direction == 3) {
//                addProjectileClipping(region, x, y, height, negative ? -64 : 64);
//                addProjectileClipping(region, x - 1, y - 1, height, negative ? -4 : 4);
//            }
//        } else if (type == 2) {
//            if (direction == 0) {
//                addProjectileClipping(region, x, y, height, 130);
//                addProjectileClipping(region, x - 1, y, height, negative ? -8 : 8);
//                addProjectileClipping(region, x, y + 1, height, negative ? -32 : 32);
//            } else if (direction == 1) {
//                addProjectileClipping(region, x, y, height, negative ? -10 : 10);
//                addProjectileClipping(region, x, y + 1, height, negative ? -32 : 32);
//                addProjectileClipping(region, x + 1, y, height, negative ? -128 : 128);
//            } else if (direction == 2) {
//                addProjectileClipping(region, x, y, height, negative ? -40 : 40);
//                addProjectileClipping(region, x + 1, y, height, negative ? -128 : 128);
//                addProjectileClipping(region, x, y - 1, height, negative ? -2 : 2);
//            } else if (direction == 3) {
//                addProjectileClipping(region, x, y, height, negative ? -160 : 160);
//                addProjectileClipping(region, x, y - 1, height, negative ? -2 : 2);
//                addProjectileClipping(region, x - 1, y, height, negative ? -8 : 8);
//            }
//        }
//        if (flag) {
//            if (type == 0) {
//                if (direction == 0) {
//                    addProjectileClipping(region, x, y, height, negative ? -0x10000 : 0x10000);
//                    addProjectileClipping(region, x - 1, y, height, negative ? -4096 : 4096);
//                } else if (direction == 1) {
//                    addProjectileClipping(region, x, y, height, negative ? -1024 : 1024);
//                    addProjectileClipping(region, x, y + 1, height, negative ? -16384 : 16384);
//                } else if (direction == 2) {
//                    addProjectileClipping(region, x, y, height, negative ? -4096 : 4096);
//                    addProjectileClipping(region, x + 1, y, height, negative ? -0x10000 : 0x10000);
//                } else if (direction == 3) {
//                    addProjectileClipping(region, x, y, height, negative ? -16384 : 16384);
//                    addProjectileClipping(region, x, y - 1, height, negative ? -1024 : 1024);
//                }
//            }
//            if (type == 1 || type == 3) {
//                if (direction == 0) {
//                    addProjectileClipping(region, x, y, height, negative ? -512 : 512);
//                    addProjectileClipping(region, x - 1, y + 1, height, negative ? -8192 : 8192);
//                } else if (direction == 1) {
//                    addProjectileClipping(region, x, y, height, negative ? -2048 : 2048);
//                    addProjectileClipping(region, x + 1, y + 1, height, negative ? -32768 : 32768);
//                } else if (direction == 2) {
//                    addProjectileClipping(region, x, y, height, negative ? -8192 : 8192);
//                    addProjectileClipping(region, x + 1, y + 1, height, negative ? -512 : 512);
//                } else if (direction == 3) {
//                    addProjectileClipping(region, x, y, height, negative ? -32768 : 32768);
//                    addProjectileClipping(region, x - 1, y - 1, height, negative ? -2048 : 2048);
//                }
//            } else if (type == 2) {
//                if (direction == 0) {
//                    addProjectileClipping(region, x, y, height, negative ? -0x10400 : 0x10400);
//                    addProjectileClipping(region, x - 1, y, height, negative ? -4096 : 4096);
//                    addProjectileClipping(region, x, y + 1, height, negative ? -16384 : 16384);
//                } else if (direction == 1) {
//                    addProjectileClipping(region, x, y, height, negative ? -5120 : 5120);
//                    addProjectileClipping(region, x, y + 1, height, negative ? -16384 : 16384);
//                    addProjectileClipping(region, x + 1, y, height, negative ? -0x10000 : 0x10000);
//                } else if (direction == 2) {
//                    addProjectileClipping(region, x, y, height, negative ? -20480 : 20480);
//                    addProjectileClipping(region, x + 1, y, height, negative ? -0x10000 : 0x10000);
//                    addProjectileClipping(region, x, y - 1, height, negative ? -1024 : 1024);
//                } else if (direction == 3) {
//                    addProjectileClipping(region, x, y, height, negative ? -81920 : 81920);
//                    addProjectileClipping(region, x, y - 1, height, negative ? -1024 : 1024);
//                    addProjectileClipping(region, x - 1, y, height, negative ? -4096 : 4096);
//                }
//            }
//        }
//    }
//
//    private void addClippingForVariableObject(Region region, int x, int y, int height, int type, int direction, boolean flag) {
//        if (type == 0) {
//            if (direction == 0) {
//                addClipping(region, x, y, height, 128);
//                addClipping(region, x - 1, y, height, 8);
//            } else if (direction == 1) {
//                addClipping(region, x, y, height, 2);
//                addClipping(region, x, y + 1, height, 32);
//            } else if (direction == 2) {
//                addClipping(region, x, y, height, 8);
//                addClipping(region, x + 1, y, height, 128);
//            } else if (direction == 3) {
//                addClipping(region, x, y, height, 32);
//                addClipping(region, x, y - 1, height, 2);
//            }
//        } else if (type == 1 || type == 3) {
//            if (direction == 0) {
//                addClipping(region, x, y, height, 1);
//                addClipping(region, x - 1, y, height, 16);
//            } else if (direction == 1) {
//                addClipping(region, x, y, height, 4);
//                addClipping(region, x + 1, y + 1, height, 64);
//            } else if (direction == 2) {
//                addClipping(region, x, y, height, 16);
//                addClipping(region, x + 1, y - 1, height, 1);
//            } else if (direction == 3) {
//                addClipping(region, x, y, height, 64);
//                addClipping(region, x - 1, y - 1, height, 4);
//            }
//        } else if (type == 2) {
//            if (direction == 0) {
//                addClipping(region, x, y, height, 130);
//                addClipping(region, x - 1, y, height, 8);
//                addClipping(region, x, y + 1, height, 32);
//            } else if (direction == 1) {
//                addClipping(region, x, y, height, 10);
//                addClipping(region, x, y + 1, height, 32);
//                addClipping(region, x + 1, y, height, 128);
//            } else if (direction == 2) {
//                addClipping(region, x, y, height, 40);
//                addClipping(region, x + 1, y, height, 128);
//                addClipping(region, x, y - 1, height, 2);
//            } else if (direction == 3) {
//                addClipping(region, x, y, height, 160);
//                addClipping(region, x, y - 1, height, 2);
//                addClipping(region, x - 1, y, height, 8);
//            }
//        }
//        if (flag) {
//            if (type == 0) {
//                if (direction == 0) {
//                    addClipping(region, x, y, height, 65536);
//                    addClipping(region, x - 1, y, height, 4096);
//                } else if (direction == 1) {
//                    addClipping(region, x, y, height, 1024);
//                    addClipping(region, x, y + 1, height, 16384);
//                } else if (direction == 2) {
//                    addClipping(region, x, y, height, 4096);
//                    addClipping(region, x + 1, y, height, 65536);
//                } else if (direction == 3) {
//                    addClipping(region, x, y, height, 16384);
//                    addClipping(region, x, y - 1, height, 1024);
//                }
//            }
//            if (type == 1 || type == 3) {
//                if (direction == 0) {
//                    addClipping(region, x, y, height, 512);
//                    addClipping(region, x - 1, y + 1, height, 8192);
//                } else if (direction == 1) {
//                    addClipping(region, x, y, height, 2048);
//                    addClipping(region, x + 1, y + 1, height, 32768);
//                } else if (direction == 2) {
//                    addClipping(region, x, y, height, 8192);
//                    addClipping(region, x + 1, y + 1, height, 512);
//                } else if (direction == 3) {
//                    addClipping(region, x, y, height, 32768);
//                    addClipping(region, x - 1, y - 1, height, 2048);
//                }
//            } else if (type == 2) {
//                if (direction == 0) {
//                    addClipping(region, x, y, height, 66560);
//                    addClipping(region, x - 1, y, height, 4096);
//                    addClipping(region, x, y + 1, height, 16384);
//                } else if (direction == 1) {
//                    addClipping(region, x, y, height, 5120);
//                    addClipping(region, x, y + 1, height, 16384);
//                    addClipping(region, x + 1, y, height, 65536);
//                } else if (direction == 2) {
//                    addClipping(region, x, y, height, 20480);
//                    addClipping(region, x + 1, y, height, 65536);
//                    addClipping(region, x, y - 1, height, 1024);
//                } else if (direction == 3) {
//                    addClipping(region, x, y, height, 81920);
//                    addClipping(region, x, y - 1, height, 1024);
//                    addClipping(region, x - 1, y, height, 4096);
//                }
//            }
//        }
//    }

    public RegionLoader() {
        super(null);
    }
}
