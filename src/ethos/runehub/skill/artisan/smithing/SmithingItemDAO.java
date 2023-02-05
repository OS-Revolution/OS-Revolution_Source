package ethos.runehub.skill.artisan.smithing;

import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolDAO;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.util.SkillDictionary;

public class SmithingItemDAO extends BetaAbstractDataAcessObject<SmithingItem> {

    private static SmithingItemDAO instance = null;

    public static SmithingItemDAO getInstance() {
        if (instance == null) {
            instance = new SmithingItemDAO();

            GatheringToolDAO.getInstance().create(
                    new GatheringTool(2347,1, SkillDictionary.Skill.SMITHING.getId(), 1.0,0,1,898)
            );

            GatheringToolDAO.getInstance().create(
                    new GatheringTool(2949,1, SkillDictionary.Skill.SMITHING.getId(), 1.0,2,1,898)
            );


            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1205,1,false,14,1,new GameItem[]{new GameItem(2349,1)}) //bronze dagger
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1351,1,false,14,1,new GameItem[]{new GameItem(2349,1)}) //bronze axe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1422,1,false,14,2,new GameItem[]{new GameItem(2349,1)}) //bronze mace
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1139,1,false,14,3,new GameItem[]{new GameItem(2349,1)}) //bronze med helm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9375,10,false,14,3,new GameItem[]{new GameItem(2349,1)}) //bronze bolts
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1277,1,false,14,4,new GameItem[]{new GameItem(2349,1)}) //bronze sword
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(819,10,false,14,4,new GameItem[]{new GameItem(2349,1)}) //bronze dart tips
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1794,1,false,14,4,new GameItem[]{new GameItem(2349,1)}) //wire
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(4819,15,false,14,4,new GameItem[]{new GameItem(2349,1)}) //bronze nails
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1321,1,false,14,5,new GameItem[]{new GameItem(2349,2)}) //bronze scimitar
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1237,1,false,14,5,new GameItem[]{new GameItem(2349,1),new GameItem(1511,1)}) //bronze spear
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(11367,1,false,14,5,new GameItem[]{new GameItem(2349,1),new GameItem(1511,1)}) //bronze hasta
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(39,15,false,14,5,new GameItem[]{new GameItem(2349,1)}) //bronze arrowtips
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9420,1,false,14,6,new GameItem[]{new GameItem(2349,1)}) //bronze cbow limbs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1291,1,false,14,6,new GameItem[]{new GameItem(2349,2)}) //bronze longsword
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(19570,5,false,14,6,new GameItem[]{new GameItem(2349,1)}) //bronze javelin heads
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1155,1,false,14,7,new GameItem[]{new GameItem(2349,2)}) //bronze fullhelm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(864,5,false,14,7,new GameItem[]{new GameItem(2349,1)}) //bronze throwing knives
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1173,1,false,14,8,new GameItem[]{new GameItem(2349,2)}) //bronze sq shield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1337,1,false,14,9,new GameItem[]{new GameItem(2349,3)}) //bronze warhammer
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1375,1,false,14,10,new GameItem[]{new GameItem(2349,3)}) //bronze battleaxe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1103,1,false,14,11,new GameItem[]{new GameItem(2349,3)}) //bronze chainbody
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1189,1,false,14,12,new GameItem[]{new GameItem(2349,3)}) //bronze kiteshield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(3095,1,false,14,13,new GameItem[]{new GameItem(2349,2)}) //bronze claws
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1307,1,false,14,14,new GameItem[]{new GameItem(2349,3)}) //bronze 2h
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1075,1,false,14,16,new GameItem[]{new GameItem(2349,3)}) //bronze legs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1087,1,false,14,16,new GameItem[]{new GameItem(2349,3)}) //bronze skirt
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1117,1,false,14,18,new GameItem[]{new GameItem(2349,5)}) //bronze body
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1203,1,false,25,15,new GameItem[]{new GameItem(2351,1)}) //iron dagger
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1349,1,false,25,16,new GameItem[]{new GameItem(2351,1)}) //iron axe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1420,1,false,25,17,new GameItem[]{new GameItem(2351,1)}) //iron mace
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1137,1,false,25,18,new GameItem[]{new GameItem(2351,1)}) //iron med helm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9377,10,false,25,18,new GameItem[]{new GameItem(2351,1)}) //iron bolts
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1279,1,false,25,19,new GameItem[]{new GameItem(2351,1)}) //iron sword
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(820,10,false,25,19,new GameItem[]{new GameItem(2351,1)}) //iron dart tips
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(4820,15,false,25,19,new GameItem[]{new GameItem(2351,1)}) //iron nails
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1323,1,false,25,20,new GameItem[]{new GameItem(2351,2)}) //iron scimitar
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1239,1,false,25,20,new GameItem[]{new GameItem(2351,1),new GameItem(1521,1)}) //iron spear
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(11369,1,false,25,20,new GameItem[]{new GameItem(2351,1),new GameItem(1521,1)}) //iron hasta
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(40,15,false,25,20,new GameItem[]{new GameItem(2351,1)}) //iron arrowtips
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9423,1,false,25,23,new GameItem[]{new GameItem(2351,1)}) //iron cbow limbs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1293,1,false,25,21,new GameItem[]{new GameItem(2351,2)}) //iron longsword
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(19572,5,false,25,21,new GameItem[]{new GameItem(2351,1)}) //iron javelin heads
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1153,1,false,25,22,new GameItem[]{new GameItem(2351,2)}) //iron fullhelm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(863,5,false,25,22,new GameItem[]{new GameItem(2351,1)}) //iron throwing knives
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1175,1,false,25,23,new GameItem[]{new GameItem(2351,2)}) //iron sq shield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1335,1,false,25,24,new GameItem[]{new GameItem(2351,3)}) //iron warhammer
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1363,1,false,25,25,new GameItem[]{new GameItem(2351,3)}) //iron battleaxe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1101,1,false,25,26,new GameItem[]{new GameItem(2351,3)}) //iron chainbody
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1191,1,false,25,27,new GameItem[]{new GameItem(2351,3)}) //iron kiteshield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(3096,1,false,25,28,new GameItem[]{new GameItem(2351,2)}) //iron claws
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1309,1,false,25,29,new GameItem[]{new GameItem(2351,3)}) //iron 2h
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1067,1,false,25,31,new GameItem[]{new GameItem(2351,3)}) //iron legs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1081,1,false,25,31,new GameItem[]{new GameItem(2351,3)}) //iron skirt
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1115,1,false,25,33,new GameItem[]{new GameItem(2351,5)}) //iron body
            );


            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1207,1,false,38,30,new GameItem[]{new GameItem(2353,1)}) //steel dagger
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1353,1,false,38,31,new GameItem[]{new GameItem(2353,1)}) //steel axe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1424,1,false,38,32,new GameItem[]{new GameItem(2353,1)}) //steel mace
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1141,1,false,38,33,new GameItem[]{new GameItem(2353,1)}) //steel med helm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9141,10,false,38,33,new GameItem[]{new GameItem(2353,1)}) //steel bolts
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1281,1,false,38,34,new GameItem[]{new GameItem(2353,1)}) //steel sword
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(821,10,false,38,34,new GameItem[]{new GameItem(2353,1)}) //steel dart tips
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1539,15,false,38,34,new GameItem[]{new GameItem(2353,1)}) //steel nails
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1325,1,false,38,35,new GameItem[]{new GameItem(2353,2)}) //steel scimitar
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1241,1,false,38,35,new GameItem[]{new GameItem(2353,1),new GameItem(1519,1)}) //steel spear
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(11371,1,false,38,35,new GameItem[]{new GameItem(2353,1),new GameItem(1519,1)}) //steel hasta
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(41,15,false,38,35,new GameItem[]{new GameItem(2353,1)}) //steel arrowtips
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9425,1,false,38,36,new GameItem[]{new GameItem(2353,1)}) //steel cbow limbs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1295,1,false,38,36,new GameItem[]{new GameItem(2353,2)}) //steel longsword
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(827,5,false,38,36,new GameItem[]{new GameItem(2353,1)}) //steel javelin heads
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1157,1,false,38,37,new GameItem[]{new GameItem(2353,2)}) //steel fullhelm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(865,5,false,38,37,new GameItem[]{new GameItem(2353,1)}) //steel throwing knives
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1177,1,false,38,38,new GameItem[]{new GameItem(2353,2)}) //steel sq shield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1339,1,false,38,39,new GameItem[]{new GameItem(2353,3)}) //steel warhammer
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1365,1,false,38,40,new GameItem[]{new GameItem(2353,3)}) //steel battleaxe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1105,1,false,38,41,new GameItem[]{new GameItem(2353,3)}) //steel chainbody
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1193,1,false,38,42,new GameItem[]{new GameItem(2353,3)}) //steel kiteshield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(3097,1,false,38,43,new GameItem[]{new GameItem(2353,2)}) //steel claws
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1311,1,false,38,44,new GameItem[]{new GameItem(2353,3)}) //steel 2h
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1069,1,false,38,46,new GameItem[]{new GameItem(2353,3)}) //steel legs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1083,1,false,38,46,new GameItem[]{new GameItem(2353,3)}) //steel skirt
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1119,1,false,38,48,new GameItem[]{new GameItem(2353,5)}) //steel body
            );


            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1209,1,false,50,50,new GameItem[]{new GameItem(2359,1)}) //mithril dagger
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1355,1,false,50,51,new GameItem[]{new GameItem(2359,1)}) //mithril axe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1428,1,false,50,52,new GameItem[]{new GameItem(2359,1)}) //mithril mace
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1143,1,false,50,53,new GameItem[]{new GameItem(2359,1)}) //mithril med helm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9379,10,false,50,53,new GameItem[]{new GameItem(2359,1)}) //mithril bolts
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1285,1,false,50,54,new GameItem[]{new GameItem(2359,1)}) //mithril sword
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(822,10,false,50,54,new GameItem[]{new GameItem(2359,1)}) //mithril dart tips
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(4822,15,false,50,54,new GameItem[]{new GameItem(2359,1)}) //mithril nails
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1329,1,false,50,55,new GameItem[]{new GameItem(2359,2)}) //mithril scimitar
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1243,1,false,50,55,new GameItem[]{new GameItem(2359,1),new GameItem(1517,1)}) //mithril spear
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(11373,1,false,50,55,new GameItem[]{new GameItem(2359,1),new GameItem(1517,1)}) //mithril hasta
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(42,15,false,50,55,new GameItem[]{new GameItem(2359,1)}) //mithril arrowtips
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(9427,1,false,50,56,new GameItem[]{new GameItem(2359,1)}) //mithril cbow limbs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1299,1,false,50,56,new GameItem[]{new GameItem(2359,2)}) //mithril longsword
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(3092,5,false,50,56,new GameItem[]{new GameItem(2359,1)}) //mithril javelin heads
            );

            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1159,1,false,50,57,new GameItem[]{new GameItem(2359,2)}) //mithril fullhelm
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(866,5,false,50,57,new GameItem[]{new GameItem(2359,1)}) //mithril throwing knives
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1181,1,false,50,58,new GameItem[]{new GameItem(2359,2)}) //mithril sq shield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1343,1,false,50,59,new GameItem[]{new GameItem(2359,3)}) //mithril warhammer
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1369,1,false,50,60,new GameItem[]{new GameItem(2359,3)}) //mithril battleaxe
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1109,1,false,50,61,new GameItem[]{new GameItem(2359,3)}) //mithril chainbody
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1197,1,false,50,62,new GameItem[]{new GameItem(2359,3)}) //mithril kiteshield
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(3099,1,false,50,63,new GameItem[]{new GameItem(2359,2)}) //mithril claws
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1315,1,false,50,64,new GameItem[]{new GameItem(2359,3)}) //mithril 2h
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1071,1,false,50,66,new GameItem[]{new GameItem(2359,3)}) //mithril legs
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1085,1,false,50,66,new GameItem[]{new GameItem(2359,3)}) //mithril skirt
            );
            SmithingItemDAO.getInstance().create(
                    new SmithingItem(1121,1,false,50,68,new GameItem[]{new GameItem(2359,5)}) //mithril body
            );
        }
        return instance;
    }

    private SmithingItemDAO() {
        super(RunehubConstants.SKILL_DB, SmithingItem.class);

    }
}
