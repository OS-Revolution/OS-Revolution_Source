package ethos.runehub.skill.artisan.runecraft;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionDAO;
import ethos.runehub.skill.node.impl.artisan.RunecraftingAltarNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class RunecraftingAltarNodeDAO extends BetaAbstractDataAcessObject<RunecraftingAltarNode> {

    private static RunecraftingAltarNodeDAO instance = null;

    public static RunecraftingAltarNodeDAO getInstance() {
        if (instance == null)
            instance = new RunecraftingAltarNodeDAO();
        return instance;
    }

    private RunecraftingAltarNodeDAO() {
        super(RunehubConstants.NODE_DB, RunecraftingAltarNode.class);
    }
}