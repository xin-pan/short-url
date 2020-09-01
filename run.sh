#!/usr/bin/env bash
cd `dirname $0`

docker stack deploy -c docker-compose.yaml dev