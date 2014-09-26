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
package com.semagia.mql.tolog;

/**
 * This class holds hints for predicates/rule invocations etc.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class Hints {

    public static enum ConstructType {
        TOPICMAP,
        TOPIC,
        ASSOCIATION,
        ROLE,
        OCCURRENCE,
        NAME,
        VARIANT;

        @Override
        public String toString() {
            switch (this) {
                case TOPICMAP: return "topicmap";
                case TOPIC: return "topic";
                case ASSOCIATION: return "association";
                case ROLE: return "role";
                case OCCURRENCE: return "occurrence";
                case NAME: return "name";
                case VARIANT: return "variant";
            }
            throw new IllegalArgumentException("Internal error");
        }

        /**
         * Helper method to create a ConstructType from lower-cased
         * strings.
         *  
         * @param name A (maybe) lower-cased name of a construct type.
         * @return A ConstructType.
         */
        static ConstructType fromString(final String name) {
            return valueOf(name.toUpperCase());
        }
    }

    public static final int UNKNOWN_COSTS = -1; 

    private static final ConstructType[] EMPTY_CONSTRUCTS = new ConstructType[0];
    private final int _costs;
    private final ConstructType[] _constructs;

    static final Hints EMPTY_HINTS = new Hints();

    private Hints() {
        this(UNKNOWN_COSTS, EMPTY_CONSTRUCTS);
    }

    Hints(final int costs) {
        this(costs, EMPTY_CONSTRUCTS);
    }

    Hints(final int costs, final ConstructType[] constructs) {
        if (constructs == null) {
            throw new IllegalArgumentException("The construct types must not be null");
        }
        _costs = costs;
        _constructs = constructs;
    }

    /**
     * Returns the estimated cost.
     * 
     * @return The estimated cost or {@link UNKNOWN_COST}.
     */
    public int getCost() {
        return _costs;
    }

    /**
     * Returns the construct type hints.
     * 
     * @return A (maybe empty) array of {@link ConstructType}, never {@code null}.
     */
    public ConstructType[] getConstructTypes() {
        return _constructs;
    }

    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("cost='");
        if (_costs == -1) {
            buff.append("unknown");
        }
        else {
            buff.append(_costs);
        }
        buff.append("' hint='");
        if (_constructs.length < 1) {
            buff.append('-');
        }
        else {
            for (int i=0; i<_constructs.length; i++) {
                if (i > 0) {
                    buff.append(' ');
                }
                buff.append(_constructs[i].toString());
            }
        }
        return buff.append('\'').toString();
    }
}
