#!/bin/bash

if docker create --name beady -p 8888:5432 -e POSTGRES_DB=beady -e POSTGRES_USER=beady -e POSTGRES_PASSWORD=beady postgres:11.1 ; then
	echo "Database successfully created"
else
	echo "Failed to create a database"
fi

