/*
 * Copyright 2010 Lars Heuer (heuer[at]semagia.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.semagia.mql;

/**
 * Represents an exception which is thrown if a syntax error or any other
 * parsing error occurs.
 * 
 * This exception may provide information at which line / column the error 
 * occurred.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 501 $ - $Date: 2010-09-09 23:43:25 +0200 (Do, 09 Sep 2010) $
 */
public final class MQLParseException extends MQLException {

    private static final long serialVersionUID = 8916672296608217515L;

    private final int _line;
    private final int _column;

    /**
     * Constructs an exception without line / column information.
     *
     * @param msg The message.
     */
    public MQLParseException(final String message) {
        this(message, -1, -1);
    }

    /**
     * Constructs an exception with a message and line / column information.
     *
     * @param msg The message.
     * @param line The line number.
     * @param column The column number.
     */
    public MQLParseException(final String message, final int line, final int column) {
        super(message);
        _line = line;
        _column = column;
    }

    /**
     * Constructs a new exception with a message, a cause and no line / column
     * information.
     *
     * @param msg The message.
     * @param cause The cause.
     */
    public MQLParseException(final String message, final Throwable cause) {
        this(message, cause, -1, -1);
    }

    /**
     * Constructs a new exception with a message, a cause and line / column
     * information.
     *
     * @param msg The message.
     * @param cause The error cause.
     * @param line The line number.
     * @param column The column number.
     */
    public MQLParseException(final String message, final Throwable cause, final int line, final int column) {
        super(message, cause);
        _line = line;
        _column = column;
    }

    /**
     * Constructs a new exception with a cause and no line / column information.
     *
     * @param cause The error cause.
     */
    public MQLParseException(final Throwable cause) {
        this(cause, -1, -1);
    }

    /**
     * Constructs a new exception with a cause and line / column information.
     *
     * @param cause The error cause.
     * @param line The line number.
     * @param column The column number.
     */
    public MQLParseException(final Throwable cause, final int line, final int column) {
        super(cause);
        _line = line;
        _column = column;
    }

    /**
     * The line number of the end of the text where the exception occurred.
     *
     * @return An integer representing the line number, or -1 if none is available.
     */
    public int getLineNumber() {
        return _line;
    }

    /**
     * The line number of the end of the text where the exception occurred.
     *
     * @return An integer representing the column number, or -1 if none is available.
     */
    public int getColumnNumber() {
        return _column;
    }
}
