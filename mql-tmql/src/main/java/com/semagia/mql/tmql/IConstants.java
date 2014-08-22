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

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 517 $ - $Date: 2010-09-12 00:26:02 +0200 (So, 12 Sep 2010) $
 */
interface IConstants {
    
    public static final String
        NS_FUNCTIONS = "http://psi.topicmaps.org/tmql/functions/"; //TODO
    ;

    public static final int
        AXIS_DEFAULT = 1,
        AXIS_TOPIC = 2,
        AXIS_ASSOCIATION = 3,
        AXIS_REIFIER = 4,
        AXIS_REIFIED = 5,
        AXIS_NAME = 6,
        AXIS_OCCURRENCE = 7,
        AXIS_ROLE = 8,
        AXIS_SUBJECT_IDENTIFIER = 9,
        AXIS_SUBJECT_LOCATOR = 10,
        AXIS_ITEM_IDENTIFIER = 11,
        AXIS_PLAYER = 12,
        AXIS_PARENT = 13,
        AXIS_TYPE = 14,
        AXIS_INSTANCE = 15,
        AXIS_SUPERTYPE = 16,
        AXIS_SUBTYPE = 17,
        AXIS_SCOPE = 18,
        AXIS_VARIANT = 19,
        AXIS_VALUE = 20,
        AXIS_DATATYPE = 21
    ;

}
