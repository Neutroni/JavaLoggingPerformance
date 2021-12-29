# JavaLoggingPerformance
Basic benchmark of some Java logging frameworks.

## Why?
Because of the recent Log4J2 news I wanted to know if it is actually a good
choice for my use cases, I need SLF4J compatibilty so Log4J2, Logback and 
java.util.logging were primary options.

## Running benchmarks
The repository contains a script benchmark.sh that runs the benchamrks for 
1,3,6,12 and 24 threads taking about 5 hours on my system to run.

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
JulFileBenchmark.julFile           thrpt   25   114,630 ±  3,042  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1401,984 ± 13,776  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1346,148 ± 17,969  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    89,834 ±  1,194  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   892,374 ± 10,551  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25  1005,967 ± 10,112  ops/ms
```

3-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   108,179 ±  9,299  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1341,834 ± 26,236  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1212,390 ± 53,360  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    53,206 ±  2,117  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   167,934 ± 11,833  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   155,246 ±  9,120  ops/ms
```

6-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   119,906 ± 26,738  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1295,252 ± 25,805  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1094,366 ± 17,614  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    56,105 ± 17,038  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   119,051 ±  3,662  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   131,143 ±  4,755  ops/ms
```

12-threads

```
Benchmark                           Mode  Cnt     Score    Error   Units
JulFileBenchmark.julFile           thrpt   25   293,294 ± 92,086  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1236,902 ± 35,157  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1074,012 ± 16,326  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    43,021 ±  1,990  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   110,404 ±  1,719  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   128,643 ±  3,945  ops/ms
```

24-threads

```
Benchmark                           Mode  Cnt     Score     Error   Units
JulFileBenchmark.julFile           thrpt   25   663,286 ± 178,896  ops/ms
Log4J2FileBenchmark.log4j2File     thrpt   25  1189,202 ±  35,187  ops/ms
LogbackFileBenchmark.logbackFile   thrpt   25  1062,962 ±   8,057  ops/ms
TerminalBenchmark.julTerminal      thrpt   25    45,007 ±   1,928  ops/ms
TerminalBenchmark.log4j2Terminal   thrpt   25   111,070 ±   3,347  ops/ms
TerminalBenchmark.logbackTerminal  thrpt   25   127,446 ±   5,059  ops/ms
```

## Bevare of variance
It seems that especially java.util.logging can have a lot of variance in the 
results, as example:

`TerminalBenchmark.julTerminal      thrpt   25   223,804 ± 184,143  ops/ms`
