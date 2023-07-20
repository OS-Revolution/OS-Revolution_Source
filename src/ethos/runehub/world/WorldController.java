package ethos.runehub.world;

import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubUtils;
import org.runehub.api.io.load.LazyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorldController {

    private static WorldController instance = null;

    public static WorldController getInstance() {
        if (instance == null)
            instance = new WorldController();
        return instance;
    }

    public Chunk loadRegion(int regionId) {
        return regionLoader.read(regionId);
    }

    public boolean isRegionLoaded(int regionId) {
        return regionLoader.containsKey(regionId);
    }

    public boolean isChunkLoaded(int chunkId) {
        return loadedChunks.contains(chunkId);
    }

    public void unloadChunk(int chunkId) {
        try {
            int index = loadedChunks.indexOf(chunkId);
            if (index != -1) {
                loadedChunks.remove(index);
//                System.out.println("unloading chunk: " + chunkId);
            } else {
                throw new NullPointerException("Attempted to unload a non-loaded chunk: " + chunkId);
            }
        } catch (Exception e) {
            System.err.println("Error unloading chunk: " + chunkId);
            e.printStackTrace();
        }
    }

    public void loadChunk(int chunkId) {
        if (!isChunkLoaded(chunkId)) {
//            System.out.println("Loading new chunks: " + chunkId);
            this.getLoadedChunks().add(chunkId);
        } else {
            System.out.println("Chunk is already loaded: " + chunkId);
        }
    }

    public List<Integer> getLoadedChunks() {
        return loadedChunks;
    }

    public void startUpdateThread() {
        worldUpdateService.scheduleAtFixedRate(() -> {
//            System.out.println("Performing World Update");
            List<Integer> loadedChunkIds = new ArrayList<>();
            PlayerHandler.getPlayers().stream()
                    .filter(Objects::nonNull)
                    .filter(player -> !player.disconnected)
                    .forEach(player -> {
                        int pChunkId = RunehubUtils.getRegionId(player.absX, player.absY);
                        if (!loadedChunkIds.contains(pChunkId)) {
                            loadedChunkIds.add(pChunkId);
                        }
                    });
            List<Integer> chunksToRemove = new ArrayList<>();
            loadedChunks.forEach(chunkId -> {
                if (!loadedChunkIds.contains(chunkId)) {
                    chunksToRemove.add(chunkId);
                }
            });
            chunksToRemove.forEach(this::unloadChunk);
//            System.out.println("Remaining loaded chunks: " + loadedChunks.size());
        }, 30, 30, TimeUnit.SECONDS);
    }

    private WorldController() {
        this.regionLoader = new RegionLoader();
//        this.loadedChunks = new HashMap<>();
//        this.chunkCache = new ChunkCache();
        this.loadedChunks = new ArrayList<>();
        this.worldUpdateService = Executors.newSingleThreadScheduledExecutor();


    }

    private final LazyLoader<Integer, Chunk> regionLoader;

    private final List<Integer> loadedChunks;
//    private final ChunkCache chunkCache;
//    private final Map<Integer, Chunk> loadedChunks;
    private final ScheduledExecutorService worldUpdateService;
}
