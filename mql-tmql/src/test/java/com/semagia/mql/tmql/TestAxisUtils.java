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

import junit.framework.TestCase;

/**
 * Tests against the {@link AxisUtils}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 511 $ - $Date: 2010-09-10 13:50:41 +0200 (Fr, 10 Sep 2010) $
 */
public class TestAxisUtils extends TestCase {

    public void testLookup() {
        assertEquals(IConstants.AXIS_DEFAULT, AxisUtils.lookup("default"));
        assertEquals(IConstants.AXIS_TOPIC, AxisUtils.lookup("topic"));
        assertEquals(IConstants.AXIS_ASSOCIATION, AxisUtils.lookup("association"));
        assertEquals(IConstants.AXIS_REIFIER, AxisUtils.lookup("reifier"));
        assertEquals(IConstants.AXIS_REIFIED, AxisUtils.lookup("reified"));
        assertEquals(IConstants.AXIS_NAME, AxisUtils.lookup("name"));
        assertEquals(IConstants.AXIS_OCCURRENCE, AxisUtils.lookup("occurrence"));
        assertEquals(IConstants.AXIS_ROLE, AxisUtils.lookup("role"));
        assertEquals(IConstants.AXIS_SUBJECT_IDENTIFIER, AxisUtils.lookup("subject-identifier"));
        assertEquals(IConstants.AXIS_SUBJECT_LOCATOR, AxisUtils.lookup("subject-locator"));
        assertEquals(IConstants.AXIS_ITEM_IDENTIFIER, AxisUtils.lookup("item-identifier"));
        assertEquals(IConstants.AXIS_PLAYER, AxisUtils.lookup("player"));
        assertEquals(IConstants.AXIS_PARENT, AxisUtils.lookup("parent"));
        assertEquals(IConstants.AXIS_TYPE, AxisUtils.lookup("type"));
        assertEquals(IConstants.AXIS_INSTANCE, AxisUtils.lookup("instance"));
        assertEquals(IConstants.AXIS_SUPERTYPE, AxisUtils.lookup("supertype"));
        assertEquals(IConstants.AXIS_SUBTYPE, AxisUtils.lookup("subtype"));
        assertEquals(IConstants.AXIS_SCOPE, AxisUtils.lookup("scope"));
        assertEquals(IConstants.AXIS_VARIANT, AxisUtils.lookup("variant"));
        assertEquals(IConstants.AXIS_VALUE, AxisUtils.lookup("value"));
        assertEquals(IConstants.AXIS_DATATYPE, AxisUtils.lookup("datatype"));
        try {
            AxisUtils.lookup("non-existent-axis");
            fail("Expected an IAE for an unknown axis");
        }
        catch (IllegalArgumentException ex) {
            // noop.
        }
        try {
            // Axis names are case-sensitive
            AxisUtils.lookup("Topic");
            fail("Expected an IAE for an unknown axis");
        }
        catch (IllegalArgumentException ex) {
            // noop.
        }
    }
}
