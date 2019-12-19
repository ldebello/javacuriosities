package ar.com.javacuriosities.hibernate.utils;

import java.sql.Savepoint;

public class SavepointContainer {

    private Savepoint savepoint;

    public Savepoint getSavepoint() {
        return savepoint;
    }

    public void setSavepoint(Savepoint savepoint) {
        this.savepoint = savepoint;
    }
}
