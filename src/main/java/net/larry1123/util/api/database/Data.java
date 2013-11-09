package net.larry1123.util.api.database;

import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import java.util.ArrayList;

public abstract class Data extends DataAccess {

    public Data(String tableName) {
        super(tableName);
    }

    public Data(String tableName, String tableSuffix) {
        super(tableName, tableSuffix);
    }

    protected void load(String[] column, Object[] searchTerms) throws DatabaseReadException {
        Database.get().load(this, column, searchTerms);
    }

    protected void update(String[] column, Object[] searchTerms) throws DatabaseWriteException {
        Database.get().update(this, column, searchTerms);
    }

    protected void insert() throws DatabaseWriteException {
        Database.get().insert(this);
    }

    protected ArrayList<DataAccess> loadAll(String[] column, Object[] searchTerms) throws DatabaseReadException {
        ArrayList<DataAccess> ret = new ArrayList<DataAccess>();
        Database.get().loadAll(this, ret, column, searchTerms);
        return ret;
    }

    protected void remove(String[] column, Object[] searchTerms) throws DatabaseWriteException {
        Database.get().remove(this.getName(), column, searchTerms);
    }

    protected void updateSchema() throws DatabaseWriteException {
        Database.get().updateSchema(this);
    }

}
