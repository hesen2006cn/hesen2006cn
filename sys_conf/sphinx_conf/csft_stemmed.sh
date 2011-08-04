#!/bin/sh
cd /usr/local/csft/

./bin/indexer dealsstemmed --rotate
./bin/indexer --merge deals dealsstemmed --rotate

echo 'index dealsstemmed merge over!'
 
