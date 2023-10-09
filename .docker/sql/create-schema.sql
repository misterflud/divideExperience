DO $$
    BEGIN

        IF NOT EXISTS(
                SELECT schema_name
                FROM information_schema.schemata
                WHERE schema_name = 'article_service'
            )
        THEN
            EXECUTE 'CREATE SCHEMA article_service';
        END IF;

    END
$$;