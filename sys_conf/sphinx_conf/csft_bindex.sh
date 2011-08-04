#!/bin/sh
cd /usr/local/csft/

./bin/indexer -c etc/csft_bindex.conf deals
 
./bin/searchd --stop
while [ -e var/data/binlog.lock ];do
	sleep 1
done

rm -f var/bdata/binlog.*
cp var/data/binlog.* var/bdata/
mv var/data var/data1
mv var/bdata var/data
mv var/data1 var/bdata
chmod 755 -R var/data
./bin/searchd 

echo 'index deals over!'
 
