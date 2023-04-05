package ethos.runehub.skill.artisan.herblore.potion;

import org.runehub.api.io.load.LazyLoader;

public class PotionFactory extends LazyLoader<Integer,Potion> {

    private static PotionFactory instance = null;

    public static PotionFactory getInstance() {
        if (instance == null)
            instance = new PotionFactory();
        return instance;
    }

    @Override
    protected Potion load(Integer key) {
        switch (key) {
            case 739:
                return new Potion(739,-1,-1,-1,-1,47);
            case 2428:
            case 121:
            case 123:
            case 125:
                return new Potion(key,2428,121,123,125,0);
            case 113:
            case 115:
            case 117:
            case 119:
                return new Potion(key,113,115,117,119,2);
            case 2432:
            case 133:
            case 135:
            case 137:
                return new Potion(key,2432,133,135,137,1);
            case 9739:
            case 9741:
            case 9743:
            case 9745:
                return new Potion(key,9739,9741,9743,9745,0,2);
            case 2430:
            case 127:
            case 129:
            case 131:
                return new Potion(key,2430,127,129,131,6,7,8,9,10);
            case 3016:
            case 3018:
            case 3020:
            case 3022:
                return new Potion(key,3016,3018,3020,3022,12);
            case 3008:
            case 3010:
            case 3012:
            case 3014:
                return new Potion(key,3008,3010,3012,3014,11);
            case 2434:
            case 139:
            case 141:
            case 143:
                return new Potion(key,2434,139,141,143,35);
            case 6685:
            case 6687:
            case 6689:
            case 6691:
                return new Potion(key,6685,6687,6689,6691,36,37,38,39,40,41);
            case 2446:
            case 175:
            case 177:
            case 179:
                return new Potion(key,2446,175,177,179,42);
            case 2448:
            case 181:
            case 183:
            case 185:
                return new Potion(key,2448,181,183,185,43);
            case 3040:
            case 3042:
            case 3044:
            case 3046:
                return new Potion(key,3040,3042,3044,3046,45);
            case 2444:
            case 169:
            case 171:
            case 173:
                return new Potion(key,2444,169,171,173,44);
            case 2452:
            case 2454:
            case 2456:
            case 2458:
                return new Potion(key,2452,2454,2456,2458,46);
            case 3024:
            case 3026:
            case 3028:
            case 3030:
                return new Potion(key,3024,3026,3028,3030,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34);
            default: throw new NullPointerException("No Effect with ID: " + key);
        }
    }

    private PotionFactory() {
        super(null);
    }
}
