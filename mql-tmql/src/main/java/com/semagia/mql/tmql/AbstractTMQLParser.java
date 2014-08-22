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
package com.semagia.mql.tmql;

import com.semagia.mql.MQLException;
import com.semagia.mql.tmql.helpers.DefaultTMQLHandler;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 518 $ - $Date: 2010-09-12 00:29:27 +0200 (So, 12 Sep 2010) $
 */
abstract class AbstractTMQLParser {

    private static final ITMQLHandler _DEFAULT_HANDLER = new DefaultTMQLHandler();

    protected ITMQLHandler _handler;

    protected AbstractTMQLParser() {
        setHandler(_DEFAULT_HANDLER);
    }

    public ITMQLHandler getHandler() {
        return _handler;
    }

    public void setHandler(final ITMQLHandler handler) {
        _handler = handler;
    }

    protected final void checkVersion(final String version) throws MQLException {
        if (!"1.0".equals(version)) {
            throw new MQLException("Expected '1.0' as version, got '" + version + "'");
        }
    }
}
