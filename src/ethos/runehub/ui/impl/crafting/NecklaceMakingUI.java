package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;

public class NecklaceMakingUI extends JewelleryMakingUI{

    @Override
    protected void onOpen() {
        JewelleryDAO.getInstance().create(
                new Jewellery(1654,1597,6,20,false,new GameItem[]{new GameItem(2357,1)}) //gold
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1656,1597,22,55,false,new GameItem[]{new GameItem(2357,1),new GameItem(1607,1)}) //sapphire
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1658,1597,29,60,false,new GameItem[]{new GameItem(2357,1),new GameItem(1605,1)}) //emerald
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1660,1597,40,75,false,new GameItem[]{new GameItem(2357,1),new GameItem(1603,1)}) //ruby
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1662,1597,56,90,false,new GameItem[]{new GameItem(2357,1),new GameItem(1601,1)}) //diamond
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1664,1597,72,105,false,new GameItem[]{new GameItem(2357,1),new GameItem(1615,1)}) //dragonstone
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(6577,1597,82,120,false,new GameItem[]{new GameItem(2357,1),new GameItem(6573,1)}) //onyx
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19535,1597,92,165,false,new GameItem[]{new GameItem(2357,1),new GameItem(19493,1)}) //zenyte
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19536,1597,92,380,false,new GameItem[]{new GameItem(2357,1),new GameItem(19529,1),new GameItem(6573,1)}) //zenyte
        );
        super.onOpen();
        listTitleLabel.setText("Necklaces");
        this.updateUI();
    }


    public NecklaceMakingUI(Player player) {
        super(player,1597);
    }
}
