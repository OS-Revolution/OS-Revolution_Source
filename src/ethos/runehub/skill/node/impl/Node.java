package ethos.runehub.skill.node.impl;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredValue;

public class Node {

    public int getId() {
        return id;
    }

    public Node(int id) {
        this.id = id;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
}
