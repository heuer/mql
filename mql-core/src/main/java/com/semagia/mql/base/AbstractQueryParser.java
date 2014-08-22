/*
 * Copyright 2010 - 2014 Lars Heuer (heuer[at]semagia.com)
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
package com.semagia.mql.base;

import java.io.IOException;

import com.semagia.mio.Source;
import com.semagia.mql.IQueryHandler;
import com.semagia.mql.IQueryParser;
import com.semagia.mql.MQLException;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public abstract class AbstractQueryParser<T extends IQueryHandler> implements IQueryParser {

    protected T _handler;

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryParser#setQueryHandler(com.semagia.mql.IQueryHandler)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setQueryHandler(final IQueryHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("The handler must not be null");
        }
        _handler = (T) handler;
    }

    /**
     * Called....
     *
     * @param src
     * @param docIRI
     * @throws IOException
     * @throws MQLException
     */
    protected abstract void doParse(final Source src) throws IOException,
            MQLException;

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryParser#parse(com.semagia.mio.Source)
     */
    @Override
    public void parse(final Source src) throws IOException, MQLException {
        if (src == null) {
            throw new IllegalArgumentException("The source must not be null");
        }
        if (_handler == null) {
            throw new IllegalStateException("The input handler was not set");
        }
        _handler.start();
        try {
            doParse(src);
        }
        finally {
            _handler.end();
            if (src.getByteStream() != null) {
                src.getByteStream().close();
            }
            if (src.getCharacterStream() != null) {
                src.getCharacterStream().close();
            }
            _handler = null;
        }
    }

}
