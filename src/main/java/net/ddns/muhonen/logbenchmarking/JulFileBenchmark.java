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
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 *
 * @author Joonas Muhonen
 */
@State(Scope.Benchmark)
public class JulFileBenchmark {

    private static final File JUL_FILE = new File("target/test-output/jul-perf.log");
    private Logger julLogger;

    /**
     *
     * @throws Exception
     */
    @Setup
    public void setUp() throws Exception {
        //Delete pre-existing log files if found
        BenchmarkUtils.deleteLogFile(JUL_FILE);

        //Create the log directory if it does not exist
        Files.createDirectories(JUL_FILE.getParentFile().toPath());

        //Set the logger settings
        try (InputStream is = TerminalBenchmark.class.getClassLoader().
                getResourceAsStream("jul-perf.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        }
        julLogger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Log using java.util.loggin to file
     */
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void julFile() {
        julLogger.fine(BenchmarkUtils.MESSAGE);
    }
}
