#!/usr/bin/env bash

export PORT=5000

export TRAKT_ACCESS_TOKEN="f3c3cfbe162ec7e16036cee523d91cc5022f1bdf9bcb1e2182b700132a6680fe"
export TRAKT_CLIENT_ID="f6e54772a85050fa843cd0f2248d21876f3288096b9c676db9f2549d068f5387"

export FILE_HOST="putio"
export PUTIO_TOKEN="UIWCQIIN"

mvn clean install exec:java

