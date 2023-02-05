package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;

public class RingMakingUI extends JewelleryMakingUI{

    @Override
    protected void onOpen() {
        JewelleryDAO.getInstance().create(
                new Jewellery(1635,1592,5,15,false,new GameItem[]{new GameItem(2357,1)}) //gold bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1637,1592,20,40,false,new GameItem[]{new GameItem(2357,1),new GameItem(1607,1)}) //sapphire
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1639,1592,27,55,false,new GameItem[]{new GameItem(2357,1),new GameItem(1605,1)}) //emerald
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1641,1592,34,70,false,new GameItem[]{new GameItem(2357,1),new GameItem(1603,1)}) //ruby
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1643,1592,43,85,false,new GameItem[]{new GameItem(2357,1),new GameItem(1601,1)}) //diamond
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1645,1592,55,100,false,new GameItem[]{new GameItem(2357,1),new GameItem(1615,1)}) //dragonstone
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(6575,1592,67,115,false,new GameItem[]{new GameItem(2357,1),new GameItem(6573,1)}) //onyx
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19538,1592,89,150,false,new GameItem[]{new GameItem(2357,1),new GameItem(19493,1)}) //zenyte
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19539,1592,89,365,false,new GameItem[]{new GameItem(2357,1),new GameItem(19529,1),new GameItem(6573,1)}) //zenyte
        );
        super.onOpen();
        listTitleLabel.setText("Rings");
        this.updateUI();
    }


    public RingMakingUI(Player player) {
        super(player,1592);
    }
}
