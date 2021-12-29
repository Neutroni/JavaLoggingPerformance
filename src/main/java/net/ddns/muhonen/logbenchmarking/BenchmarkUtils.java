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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

/**
 *
 * @author Joonas Muhonen
 */
public class BenchmarkUtils {

    /**
     * Message to log
     */
    public static final String MESSAGE = "This is a debug message";

    /**
     * Helper to delete temporary log files.
     *
     * Displays the deletion and errors to System.out
     *
     * @param file Log file to delete
     */
    public static void deleteLogFile(final File file) {
        try {
            Files.delete(file.toPath());
            System.out.println("Deleted log file " + file);
        } catch (NoSuchFileException e) {
            System.out.println("Log file does not exist " + file);
        } catch (IOException ex) {
            System.out.println("Invalid File: " + file + " passed for log file deletion\n" + ex.getMessage());
        }
    }
}
