package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements"
*/
public class V1653357666595__CreateTableRole extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            using(configuration)
                    .createTableIfNotExists("role")
                        .column("id", BIGINT.identity(true))
                        .column("name", VARCHAR(100).nullable(false))
                    .constraints(
                        constraint("role_pk").primaryKey("id"),                            
                        constraint("role_unique_name").unique("name"))
                    .execute();
        });
    }
}