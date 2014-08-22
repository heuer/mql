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

import java.io.IOException;

import com.semagia.mio.Source;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 558 $ - $Date: 2010-09-27 12:36:13 +0200 (Mo, 27 Sep 2010) $
 */
public interface IQueryParser {

    /**
     * 
     *
     * @param handler
     */
    public void setQueryHandler(IQueryHandler handler);

    /**
     * 
     *
     * @param src
     * @throws IOException
     * @throws MQLException
     */
    public void parse(Source src) throws IOException, MQLException;

}
