package ethos.runehub.world;

import org.runehub.api.io.load.LazyLoader;

public class ChunkCache extends LazyLoader<Integer,Chunk> {

    protected Chunk load(Integer key) {
        return new Chunk(key);
    }

    public ChunkCache() {
        super(null);
    }

}
