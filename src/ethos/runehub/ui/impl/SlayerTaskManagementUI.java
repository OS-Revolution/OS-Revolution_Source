package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.slayer.Slayer;
import ethos.runehub.skill.support.slayer.SlayerAssignment;
import ethos.runehub.skill.support.slayer.SlayerAssignmentDAO;

public class SlayerTaskManagementUI extends SelectionParentUI{

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Task Management");
        listTitleLabel.setText(type + " Tasks");
        rewardLabel.setText("Removal Fee");
        this.drawListItems();
        this.updateUI();
    }

    private void drawListItems() {
        this.clearList();
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] != 0L) {
                final int index = i;
                final SlayerAssignment assignment = SlayerAssignmentDAO.getInstance().read(taskArray[i]);
                listItemLabels[i].setText(assignment.getCategory());
                listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(index, assignment));
            }
        }
    }

    private void drawSelectedIndex(int index,SlayerAssignment assignment) {
        if (type.contains("Blocked")) {
            infoH1Label.setText("Unblock " + assignment.getCategory() + "?");
            optionOneLabel.setText("Unblock");
            items[0] = new GameItem(995, RunehubConstants.UNBLOCK_TASK_COST);
            this.registerButton(actionEvent -> {
                if (this.getPlayer().getItems().playerHasItem(995,RunehubConstants.UNBLOCK_TASK_COST)) {
                    this.getPlayer().getSkillController().getSlayer().unblockTask(index);
                    this.taskArray[index] = 0L;
                    infoH1Label.setText("");
                    optionOneLabel.setText("");
                    items[0] = new GameItem(-1, 0);
                    this.drawListItems();
                    this.updateUI();
                }
            }, 150111);
        } else {
            infoH1Label.setText("Un-Prefer " + assignment.getCategory() + "?");
            optionOneLabel.setText("Un-Prefer");
            items[0] = new GameItem(995, RunehubConstants.UNBLOCK_TASK_COST);
            this.registerButton(actionEvent -> {
                if (this.getPlayer().getItems().playerHasItem(995,RunehubConstants.UNBLOCK_TASK_COST)) {
                    this.getPlayer().getSkillController().getSlayer().unpreferTask(index);
                    this.taskArray[index] = 0L;
                    infoH1Label.setText("");
                    optionOneLabel.setText("");
                    items[0] = new GameItem(-1, 0);
                    this.drawListItems();
                    this.updateUI();
                }
            }, 150111);
        }
        this.updateUI();
    }


    public SlayerTaskManagementUI(Player player, long[] taskArray, String type) {
        super(player);
        this.taskArray = taskArray;
        this.type = type;
    }

    private final String type;
    private long[] taskArray;
}
