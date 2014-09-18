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
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class Hints {

    public static final int
        TOPICMAP = 1,
        TOPIC = 2,
        ASSOCIATION = 3,
        ROLE = 4,
        OCCURRENCE = 5,
        NAME = 6,
        VARIANT = 7;

    public static final int UNKNOWN_COSTS = -1; 

    static int[] EMPTY_CONSTRUCTS = new int[0];
    private final int _costs;
    private final int[] _constructs;

    static Hints EMPTY_HINTS = new Hints();

    Hints() {
        this(UNKNOWN_COSTS, EMPTY_CONSTRUCTS);
    }

    Hints(int costs) {
        this(costs, EMPTY_CONSTRUCTS);
    }

    Hints(int[] constructs) {
        this(UNKNOWN_COSTS, constructs);
    }

    Hints(int costs, int[] constructs) {
        _costs = costs;
        _constructs = constructs;
    }

    public int getCost() {
        return _costs;
    }

    public int[] getConstructs() {
        return _constructs;
    }

}
