#!/bin/bash

if docker start beady ; then
	echo "Database started successfully"
else
	echo "Failed to start a database"
fi
