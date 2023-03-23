package ethos.runehub.skill.support.slayer.master.impl;

import ethos.runehub.skill.support.slayer.SlayerTask;
import ethos.runehub.skill.support.slayer.SlayerUtils;
import ethos.runehub.skill.support.slayer.master.SlayerMaster;
import org.runehub.api.model.math.impl.IntegerRange;

public class TuraelSlayerMaster extends SlayerMaster {

    public TuraelSlayerMaster() {
        super(401,   new SlayerTask[]{
//                        SlayerUtils.createTaskForCategory("banshees", 20, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(1025526826525805341L, 20, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(3603297688527391667L, 5, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(6853472087209626868L, 1, 6, new long[]{-1813912594169246384L}, new IntegerRange(15,50),-1),
                        new SlayerTask(-8369018869365653266L, 13, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1141411474617317903L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(4895597504303677792L, 10, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(538605612646288530L, 15, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(2875949606167658816L, 5, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-5812452093955977313L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(932877277895740829L, 15, 7, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(5156721740240340862L, 6, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1892045768008447818L, 13, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(7525515536511421085L, 1, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-3050582876871113953L, 20, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-518258673358258135L, 15, 6, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(-8598879298038378780L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-8564549073105370520L, 7, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-7096098567790965283L, 1, 6, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-3306869684914468602L, 1, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1915300238322152628L, 7, 7, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(-3730357465968524409L, 15, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(2633716304376317904L, 1, 6, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-6095611682120842413L, 20, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(7037424871780140015L, 10, 7, new long[]{}, new IntegerRange(15,50),-1)
                },
                0, 0, 0, 0, 0, 0, false);
    }
}
