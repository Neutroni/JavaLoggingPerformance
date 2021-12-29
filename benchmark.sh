#!/bin/sh
DATE=$(date  '+%Y-%m-%dT%H%M')
for TC in 1 3 6 12 24;
do
    java -jar target/LogBenchmarking-1.0-SNAPSHOT.jar -t $TC 2> /dev/null | tee "results-${DATE}-$TC.txt"
done
