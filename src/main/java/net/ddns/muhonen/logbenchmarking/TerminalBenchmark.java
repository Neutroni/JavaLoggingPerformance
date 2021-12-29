/*
 * The MIT License
 *
 * Copyright 2020 Joonas Muhonen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.ddns.muhonen.logbenchmarking;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 *
 * @author Joonas Muhonen
 */
@State(Scope.Benchmark)
public class TerminalBenchmark {

    private org.apache.logging.log4j.Logger log4j2Logger;
    private org.slf4j.Logger slf4jLogger;
    private java.util.logging.Logger julLogger;

    /**
     *
     * @throws Exception
     */
    @Setup
    public void setUp() throws Exception {
        System.setProperty("log4j.configurationFile", "log4j2-terminal.xml");
        System.setProperty("logback.configurationFile", "logback-terminal.xml");
        System.setProperty("tinylog.configuration", "tinylog-terminal.properties");

        log4j2Logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
        slf4jLogger = org.slf4j.LoggerFactory.getLogger(this.getClass());

        try (InputStream is = TerminalBenchmark.class.getClassLoader().
                getResourceAsStream("jul-terminal.properties")) {
            java.util.logging.LogManager.getLogManager().readConfiguration(is);
        }
        julLogger = java.util.logging.Logger.getLogger(this.getClass().getName());
    }

    /**
     * Remove set properties
     */
    @TearDown
    public void tearDown() {
        System.clearProperty("log4j.configurationFile");
        System.clearProperty("logback.configurationFile");
        System.clearProperty("tinylog.configuration");
    }

    /**
     * Log using Logback
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void logbackTerminal() {
        slf4jLogger.debug(BenchmarkUtils.MESSAGE);
    }

    /**
     * Log using Log4J2
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void log4j2Terminal() {
        log4j2Logger.debug(BenchmarkUtils.MESSAGE);
    }

    /**
     * Log using java.util.loggin
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void julTerminal() {
        julLogger.fine(BenchmarkUtils.MESSAGE);
    }

    /**
     * Log using tinylog
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void tinylogTerminal() {
        org.tinylog.Logger.debug(BenchmarkUtils.MESSAGE);
    }
}
