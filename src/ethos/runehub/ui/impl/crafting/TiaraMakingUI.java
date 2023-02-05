package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;

public class TiaraMakingUI extends JewelleryMakingUI{

    @Override
    protected void onOpen() {
        JewelleryDAO.getInstance().create(
                new Jewellery(5525,5523,22,53,false,new GameItem[]{new GameItem(2355,1)}) //base
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5527,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1438,1)}) //air
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5529,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1448,1)}) //min
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5531,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1444,1)}) //water
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5535,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1440,1)}) //earth
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5537,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1442,1)}) //fire
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5533,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1446,1)}) //body
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5539,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1454,1)}) //cosmic
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5543,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1452,1)}) //chaos
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(9106,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(9075,10)}) //astral
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5541,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1462,1)}) //nature
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5545,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1458,1)}) //law
        );
        JewelleryDAO.getInstance().create(
                new Jewellery(5547,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(1456,1)}) //death
        );
//        JewelleryDAO.getInstance().create(
//                new Jewellery(19538,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(19493,1)}) //blood
//        );
//        JewelleryDAO.getInstance().create(
//                new Jewellery(19538,5523,22,53,false,new GameItem[]{new GameItem(2355,1),new GameItem(19493,1)}) //soul
//        );
        super.onOpen();
        listTitleLabel.setText("Tiaras");
        this.updateUI();
    }


    public TiaraMakingUI(Player player) {
        super(player,5523);
    }
}
