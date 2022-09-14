#!/bin/bash

if docker start beady-postgres ; then
	echo "Database started successfully"
else
	echo "Failed to start a database"
fi
