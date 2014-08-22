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
package com.semagia.mql.tolog;

import com.semagia.mql.tolog.helpers.DefaultTologHandler;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 518 $ - $Date: 2010-09-12 00:29:27 +0200 (So, 12 Sep 2010) $
 */
abstract class AbstractTologParser {

    private static final ITologHandler _DEFAULT_HANDLER = new DefaultTologHandler();

    protected ITologHandler _handler;

    protected AbstractTologParser() {
        setHandler(_DEFAULT_HANDLER);
    }

    public ITologHandler getHandler() {
        return _handler;
    }

    public void setHandler(final ITologHandler handler) {
        _handler = handler;
    }

    
    protected static boolean isBuiltinPredicate(final String name) {
        return TologUtils.isBuiltinPredicate(name);
    }

    protected static final class Tuple {
        
        public static final int 
            IDENT = 100,
            OID = 101,
            IID = 102,
            SID = 103,
            SLO = 104,
            IRI = SID,
            DATE_TIME = 105,
            DATE = 106,
            DECIMAL = 107,
            INTEGER = 108;

        public final int kind;
        public final String value;

        protected Tuple(final int kind, final String value) {
            this.kind = kind;
            this.value = value;
        }
    }
}
