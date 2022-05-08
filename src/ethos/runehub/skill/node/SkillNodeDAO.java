package ethos.runehub.skill.node;

import ethos.runehub.skill.node.impl.SkillNode;
import org.runehub.api.io.data.impl.AbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class SkillNodeDAO extends AbstractDataAcessObject<SkillNode> {

    private static SkillNodeDAO instance = null;

    public static SkillNodeDAO getInstance() {
        if (instance == null)
            instance = new SkillNodeDAO();
        return instance;
    }

    private SkillNodeDAO() {
        super("./Data/runehub/db/nodes.db", SkillNode.class);
        this.getDatabaseServiceProvider().createTable();
    }
}
