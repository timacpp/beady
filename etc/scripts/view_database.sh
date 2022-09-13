#!/bin/bash

docker exec -ti beady sh -c "export PGPASSWORD=beady & psql -h localhost -p 5432 -U beady"
