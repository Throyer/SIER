package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements/"
*/
public class V1653357830878__CreateTableResidentialBuilding extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            using(configuration)
                .createTableIfNotExists("residential_building")
                    .column("id", BIGINT.identity(true))
                    .column("official_name", VARCHAR(100).nullable(true))
                    .column("name", VARCHAR(100).nullable(false))
                    .column("data_source", VARCHAR(100).nullable(true))
                    .column("additional_information", VARCHAR(255).nullable(true))
                    .column("collected_at", DATE.nullable(true))
                    .column("foundation_date", DATE.nullable(true))
                    .column("postal_code", VARCHAR(100).nullable(true))
                    .column("street", VARCHAR(100).nullable(false))
                    .column("neighborhood", VARCHAR(100).nullable(false))
                    .column("city", VARCHAR(100).nullable(false))
                    .column("state", VARCHAR(100).nullable(false))
                    .column("address_number", VARCHAR(100).nullable(true))
                    .column("number_of_floors", INTEGER.defaultValue(1))
                    .column("created_at", TIMESTAMP.defaultValue(currentTimestamp()))
                    .column("updated_at", TIMESTAMP.nullable(true))
                    .column("deleted_at", TIMESTAMP.nullable(true))
                    .column("created_by", BIGINT.nullable(true))
                    .column("updated_by", BIGINT.nullable(true))
                    .column("deleted_by", BIGINT.nullable(true))
                .constraints(
                    constraint("residential_building_pk").primaryKey("id"),
                    constraint("residential_building_created_by_fk").foreignKey("created_by").references("user", "id"),                        
                    constraint("residential_building_updated_by_fk").foreignKey("updated_by").references("user", "id"),                        
                    constraint("residential_building_deleted_by_fk").foreignKey("deleted_by").references("user", "id"))
                .execute();
        });
    }
}