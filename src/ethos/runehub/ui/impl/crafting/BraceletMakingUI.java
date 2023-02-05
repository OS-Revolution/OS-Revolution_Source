package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;

public class BraceletMakingUI extends JewelleryMakingUI{

    @Override
    protected void onOpen() {
        JewelleryDAO.getInstance().create(
                new Jewellery(11069,11065,7,25,false,new GameItem[]{new GameItem(2357,1)}) //gold bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11072,11065,23,60,false,new GameItem[]{new GameItem(2357,1),new GameItem(1607,1)}) //sapphire bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11077,11065,30,65,false,new GameItem[]{new GameItem(2357,1),new GameItem(1605,1)}) //emerald bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11086,11065,42,80,false,new GameItem[]{new GameItem(2357,1),new GameItem(1603,1)}) //ruby bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11093,11065,58,95,false,new GameItem[]{new GameItem(2357,1),new GameItem(1601,1)}) //diamond bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11115,11065,74,110,false,new GameItem[]{new GameItem(2357,1),new GameItem(1615,1)}) //dragonstone bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(11130,11065,84,125,false,new GameItem[]{new GameItem(2357,1),new GameItem(6573,1)}) //onyx bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19532,11065,95,180,false,new GameItem[]{new GameItem(2357,1),new GameItem(19493,1)}) //dragonstone bracelet
        );
        super.onOpen();
        listTitleLabel.setText("Bracelets");
        this.updateUI();
    }


    public BraceletMakingUI(Player player) {
        super(player,11065);
    }
}
