%{
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

import java.util.ArrayList;
import java.util.List;

import com.semagia.mql.MQLException;

/**
 * This TMQL parser utilizes the AbstractTMQLParser and is responsible for 
 * the grammar.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 505 $ - $Date: 2010-09-10 08:57:49 +0200 (Fr, 10 Sep 2010) $
 */
class RealTMQLParser extends AbstractTMQLParser { 

%}

%%

instance    : 
            ;

axis        : IDENT COLON_COLON
            ;

%%
}
