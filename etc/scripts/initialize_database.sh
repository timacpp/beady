#!/bin/bash

docker cp ../sql/initial_inserts.sql beady:/initial_inserts.sql
docker exec -ti beady sh -c "export PGPASSWORD=beady & psql -h localhost -d beady -p 5432 -U beady -f initial_inserts.sql"
