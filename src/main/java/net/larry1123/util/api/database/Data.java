package net.larry1123.util.api.database;

import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import java.util.ArrayList;
import java.util.Map;

public abstract class Data extends DataAccess {

    public Data(String tableName) {
        super(tableName);
    }

    public Data(String tableName, String tableSuffix) {
        super(tableName, tableSuffix);
    }

    protected void load(Map<String, Object> filter) throws DatabaseReadException {
        Database.get().load(this, filter);
    }

    protected void update(Map<String, Object> filter) throws DatabaseWriteException {
        Database.get().update(this, filter);
    }

    protected void insert() throws DatabaseWriteException {
        Database.get().insert(this);
    }

    protected ArrayList<DataAccess> loadAll(Map<String, Object> filter) throws DatabaseReadException {
        ArrayList<DataAccess> ret = new ArrayList<DataAccess>();
        Database.get().loadAll(this, ret, filter);
        return ret;
    }

    protected void remove(Map<String, Object> filter) throws DatabaseWriteException {
        Database.get().remove(this, filter);
    }

    protected void updateSchema() throws DatabaseWriteException {
        Database.get().updateSchema(this);
    }

}
