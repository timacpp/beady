#!/bin/bash

docker cp ../../sql/initial_inserts.sql beady-postgres:/initial_inserts.sql
docker exec -ti beady-postgres sh -c "export PGPASSWORD=beady & psql -h localhost -d beady -p 5432 -U beady -f initial_inserts.sql"
