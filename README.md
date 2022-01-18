# JavaLoggingPerformance
Basic benchmark of some Java logging frameworks.

## Why?
Because of the recent Log4J2 news I wanted to know if it is actually a good
choice for my use cases, I need SLF4J compatibilty so Log4J2, Logback and 
java.util.logging were primary options.

## Running benchmarks
The repository contains a script benchmark.sh that runs the benchamrks for 
1,3,6,12 and 24 threads taking about 6 hours on my system to run.

Use `java -jar target/LogBenchmarking-1.0-SNAPSHOT.jar "regex-here"` to run 
single benchmark where `regex-here` is a regex for benchamrk name.

Use `java -jar target/LogBenchmarking-1.0-SNAPSHOT.jar -h` to see available 
options for JMH settings.

## Results
Specs: Ryzen 5-3600 16GB 3200MT/s TOSHIBA DT01ACA300
Terminal logged to /dev/null from STDERR

1 thread

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   117,159 ±  1,383  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1401,945 ± 12,292  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1346,884 ± 10,948  ops/ms
TinylogFileBenchmark.tinylogFile   thrpt   25   722,072 ±  7,633  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    89,711 ±  0,735  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   934,817 ± 12,766  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25  1049,577 ±  4,005  ops/ms
TerminalBenchmark.tinylogTerminal  thrpt   25   276,991 ±  2,638  ops/ms
```

3-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   110,102 ± 12,541  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1323,151 ± 35,186  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1276,000 ± 74,527  ops/ms
TinylogFileBenchmark.tinylogFile   thrpt   25  1645,994 ± 32,255  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    54,522 ±  2,730  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   163,248 ± 10,364  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   161,595 ±  9,603  ops/ms
TerminalBenchmark.tinylogTerminal  thrpt   25   141,867 ±  8,668  ops/ms
```

6-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   110,705 ± 19,199  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1264,825 ± 26,826  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1078,737 ± 18,593  ops/ms
TinylogFileBenchmark.tinylogFile   thrpt   25  1445,670 ± 19,647  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    45,536 ±  2,021  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   123,722 ±  6,733  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   135,570 ±  5,696  ops/ms
TerminalBenchmark.tinylogTerminal  thrpt   25    83,389 ±  4,011  ops/ms
```

12-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   277,155 ± 101,244  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1177,690 ±  24,713  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1055,266 ±  12,686  ops/ms
TinylogFileBenchmark.tinylogFile   thrpt   25  1383,366 ±  69,844  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    43,476 ±   1,221  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   111,755 ±   2,469  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   128,434 ±   7,287  ops/ms
TerminalBenchmark.tinylogTerminal  thrpt   25    79,299 ±   1,889  ops/ms
```

24-threads

```
Benchmark                           Mode  Cnt     Score     Error   Units
JulFileBenchmark.julFile           thrpt   25   604,850 ± 226,732  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1113,542 ±  31,949  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1056,069 ±  11,109  ops/ms
TinylogFileBenchmark.tinylogFile   thrpt   25  1249,064 ±  22,564  ops/mss
TerminalBenchmark.julTerminal      thrpt   25    44,809 ±   2,286  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   113,197 ±   2,841  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   127,418 ±   5,973  ops/ms
TerminalBenchmark.tinylogTerminal  thrpt   25    75,604 ±   2,682  ops/ms
```

## Bevare of variance
It seems that especially java.util.logging can have a lot of variance in the 
results, as example:

`TerminalBenchmark.julTerminal      thrpt   25   223,804 ± 184,143  ops/ms`
