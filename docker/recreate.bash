#!/bin/bash

echo "Stopping and removing containers..."
docker-compose down

echo "Removing PostgreSQL volume..."
docker volume rm docker_db_vol

echo "Removing Zookeeper volume..."
docker volume rm docker_zookeeper_vol
docker volume rm docker_zookeeper_datalog

echo "Removing Kafka volume..."
docker volume rm docker_kafka_vol

echo "Starting up containers..."

docker-compose up -d
#docker-compose up

echo "Containers started successfully!"
