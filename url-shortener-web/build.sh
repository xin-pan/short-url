#!/usr/bin/env bash
cd `dirname $0`

docker build -t seanpan/shorten-url-web .
docker push seanpan/shorten-url-web