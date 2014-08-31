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

import com.semagia.mio.IRef;
import com.semagia.mio.helpers.Ref;


final class TologReference extends Ref {

    public static final int
        IID = IRef.ITEM_IDENTIFIER,
        SID = IRef.SUBJECT_IDENTIFIER,
        SLO = IRef.SUBJECT_LOCATOR,
        IRI = SID,
        IDENT = 1000,
        VARIABLE = 1001,
        STRING = 1002,
        QNAME = 1003,
        OID = 1004,
        PARAMETER = 1005;

    TologReference(String iri, int type) {
        super(iri, type);
    }

    public String getValue() {
        return super.getIRI();
    }

    final static TologReference createParameter(final String name) {
        return new TologReference(name, PARAMETER);
    }

    final static TologReference createVariable(final String name) {
        return new TologReference(name, VARIABLE);
    }

    final static TologReference createString(final String name) {
        return new TologReference(name, STRING);
    }

    public static TologReference createIdent(String name) {
        return new TologReference(name, IDENT);
    }

    public static TologReference createIRI(String iri) {
        return new TologReference(iri, IRI);
    }

    public static TologReference createSID(String iri) {
        return new TologReference(iri, SID);
    }

    public static TologReference createSLO(String iri) {
        return new TologReference(iri, SLO);
    }

    public static TologReference createOID(String ident) {
        return new TologReference(ident, OID);
    }

    public static TologReference createIID(String iri) {
        return new TologReference(iri, IID);
    }

}
