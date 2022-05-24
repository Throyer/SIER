package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements/
*/
public class V1653357675090__CreateTableUser extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            using(configuration)
                    .createTableIfNotExists("user")
                        .column("id", BIGINT.identity(true))
                        .column("name", VARCHAR(100).nullable(false))
                        .column("nick_name", VARCHAR(100).nullable(false))
                        .column("class_year", VARCHAR(100).nullable(true))
                        .column("email", VARCHAR(100).nullable(true))
                        .column("deleted_email", VARCHAR(100).nullable(true))
                        .column("password", VARCHAR(100).nullable(false))
                        .column("active", BOOLEAN.defaultValue(true))
                        .column("created_at", TIMESTAMP.defaultValue(currentTimestamp()))
                        .column("updated_at", TIMESTAMP.nullable(true))
                        .column("deleted_at", TIMESTAMP.nullable(true))
                        .column("role_id", BIGINT.nullable(true))
                    .constraints(
                        constraint("user_pk").primaryKey("id"),                            
                        constraint("user_unique_email").unique("email"),                        
                        constraint("user_deleted_by_fk").foreignKey("role_id").references("role", "id"))
                    .execute();
        });
    }
}