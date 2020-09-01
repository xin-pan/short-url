#!/usr/bin/env bash
cd `dirname $0`

mvn clean install
docker build -t seanpan/shorten-url-rest .
docker push seanpan/shorten-url-rest