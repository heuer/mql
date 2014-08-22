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
 * Internal class to handle TMQL axis lookups, i.e. by name.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 517 $ - $Date: 2010-09-12 00:26:02 +0200 (So, 12 Sep 2010) $
 */
final class AxisUtils implements IConstants {

    private AxisUtils() {
        // noop.
    }

    /**
     * Returns the axis constant for the provided name.
     *
     * @param name The axis name
     * @throws IllegalArgumentException If the provided name does not represent an axis.
     */
    public static int lookup(final String name) throws IllegalArgumentException {
        if ("topic".equals(name)) {
            return AXIS_TOPIC;
        }
        if ("association".equals(name)) {
            return AXIS_ASSOCIATION;
        }
        if ("role".equals(name)) {
            return AXIS_ROLE;
        }
        if ("occurrence".equals(name)) {
            return AXIS_OCCURRENCE;
        }
        if ("name".equals(name)) {
            return AXIS_NAME;
        }
        if ("reifier".equals(name)) {
            return AXIS_REIFIER;
        }
        if ("reified".equals(name)) {
            return AXIS_REIFIED;
        }
        if ("subject-identifier".equals(name)) {
            return AXIS_SUBJECT_IDENTIFIER;
        }
        if ("subject-locator".equals(name)) {
            return AXIS_SUBJECT_LOCATOR;
        }
        if ("item-identifier".equals(name)) {
            return AXIS_ITEM_IDENTIFIER;
        }
        if ("player".equals(name)) {
            return AXIS_PLAYER;
        }
        if ("parent".equals(name)) {
            return AXIS_PARENT;
        }
        if ("type".equals(name)) {
            return AXIS_TYPE;
        }
        if ("instance".equals(name)) {
            return AXIS_INSTANCE;
        }
        if ("supertype".equals(name)) {
            return AXIS_SUPERTYPE;
        }
        if ("subtype".equals(name)) {
            return AXIS_SUBTYPE;
        }
        if ("scope".equals(name)) {
            return AXIS_SCOPE;
        }
        if ("variant".equals(name)) {
            return AXIS_VARIANT;
        }
        if ("value".equals(name)) {
            return AXIS_VALUE;
        }
        if ("datatype".equals(name)) {
            return AXIS_DATATYPE;
        }
        if ("default".equals(name)) {
            return AXIS_DEFAULT;
        }
        throw new IllegalArgumentException("Unknown axis '" + name + "'");
    }

}
