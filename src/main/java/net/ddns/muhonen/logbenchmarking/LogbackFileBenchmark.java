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

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joonas Muhonen
 */
@State(Scope.Benchmark)
public class LogbackFileBenchmark {

    private static final File LOGBACK_FILE = new File("target/test-output/logback-perf.log");
    private Logger slf4jLogger;

    /**
     *
     * @throws Exception
     */
    @Setup
    public void setUp() throws Exception {
        System.setProperty("logback.configurationFile", "logback-perf.xml");

        //Delete pre-existing log files if found
        BenchmarkUtils.deleteLogFile(LOGBACK_FILE);

        slf4jLogger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Remove set properties
     */
    @TearDown
    public void tearDown() {
        System.clearProperty("logback.configurationFile");
    }

    /**
     * Log using Logback
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void logbackFile() {
        slf4jLogger.debug(BenchmarkUtils.MESSAGE);
    }
}
