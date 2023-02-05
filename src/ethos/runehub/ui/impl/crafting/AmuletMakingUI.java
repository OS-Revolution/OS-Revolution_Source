package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;

public class AmuletMakingUI extends JewelleryMakingUI{

    @Override
    protected void onOpen() {
        JewelleryDAO.getInstance().create(
                new Jewellery(1673,1595,8,30,false,new GameItem[]{new GameItem(2357,1)}) //gold bracelet
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1675,1595,24,65,false,new GameItem[]{new GameItem(2357,1),new GameItem(1607,1)}) //sapphire
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1677,1595,31,70,false,new GameItem[]{new GameItem(2357,1),new GameItem(1605,1)}) //emerald
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1679,1595,50,85,false,new GameItem[]{new GameItem(2357,1),new GameItem(1603,1)}) //ruby
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1681,1595,70,100,false,new GameItem[]{new GameItem(2357,1),new GameItem(1601,1)}) //diamond
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(1683,1595,80,150,false,new GameItem[]{new GameItem(2357,1),new GameItem(1615,1)}) //dragonstone
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(6579,1595,90,165,false,new GameItem[]{new GameItem(2357,1),new GameItem(6573,1)}) //onyx
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19501,1595,98,200,false,new GameItem[]{new GameItem(2357,1),new GameItem(19493,1)}) //zenyte
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(19502,1595,98,400,false,new GameItem[]{new GameItem(2357,1),new GameItem(19529,1),new GameItem(6573,1)}) //zenyte
        );
        super.onOpen();
        listTitleLabel.setText("Amulets");
        this.updateUI();
    }


    public AmuletMakingUI(Player player) {
        super(player,1595);
    }
}
