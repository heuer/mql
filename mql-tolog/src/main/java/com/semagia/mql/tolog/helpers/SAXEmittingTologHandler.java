/**
 * 
 */
package com.semagia.mql.tolog.helpers;

import org.xml.sax.ContentHandler;

import com.semagia.mql.MQLException;
import com.semagia.mql.base.AbstractSAXEmittingQueryHandler;
import com.semagia.mql.tolog.ITologHandler;

/**
 * {@link ITologHandler} implementations which translates the events into SAX events.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class SAXEmittingTologHandler extends AbstractSAXEmittingQueryHandler implements
        ITologHandler {

    private static final String TOLOG_NS = "http://psi.semagia.com/tolog-xml/";

    public SAXEmittingTologHandler(ContentHandler handler) {
        super(handler, TOLOG_NS);
    }

    private static String prefixKindToString(final int kind) throws MQLException {
        switch (kind) {
            case PREFIX_KIND_SUBJECT_IDENTIFIER: return "subject-identifier";
            case PREFIX_KIND_SUBJECT_LOCATOR: return "subject-locator";
            case PREFIX_KIND_ITEM_IDENTIFIER: return "item-identifier";
            case PREFIX_KIND_MODULE: return "module";
        }
        throw new MQLException("Unknown prefix kind '" + kind + "'");
    }

    @Override
    public void option(String key, String value) throws MQLException {
        super.emptyElement("option", new String[][] {{"key", key}, {"value", value}});
    }

    @Override
    public void namespace(String prefix, String iri, int kind) throws MQLException {
        super.emptyElement("namespace", new String[][]{{"prefix", prefix}, {"iri", iri}, {"kind", prefixKindToString(kind)}});
    }

    @Override
    public void startFunctionCall(String name) throws MQLException {
        super.startElement("function-call", "name", name);
    }

    @Override
    public void endFunctionCall() throws MQLException {
        super.endElement("function-call");
    }

    @Override
    public void startMerge() throws MQLException {
        super.startElement("merge");
    }

    @Override
    public void endMerge() throws MQLException {
        super.endElement("merge");
    }

    @Override
    public void startAssociationPredicate() throws MQLException {
        super.startElement("association-predicate");
    }

    @Override
    public void endAssociationPredicate() throws MQLException {
        super.endElement("association-predicate");
    }

    @Override
    public void startBuiltinPredicate(String name) throws MQLException {
        super.startElement("builtin-predicate", "name", name);
    }

    @Override
    public void endBuiltinPredicate() throws MQLException {
        super.endElement("builtin-predicate");
    }

    @Override
    public void startRule(String name, String[] variables) throws MQLException {
        super.startElement("rule", "name", name);
        for (String var: variables) {
            super.variable(var);
        }
        super.startElement("body");
    }

    @Override
    public void endRule() throws MQLException {
        super.endElement("body");
        super.endElement("rule");
    }

    @Override
    public void startInfixPredicate(String name) throws MQLException {
        super.startElement("infix-predicate", "name", name);
    }

    @Override
    public void endInfixPredicate() throws MQLException {
        super.endElement("infix-predicate");
    }

    @Override
    public void startFragment() throws MQLException {
        super.startElement("fragment");
    }

    @Override
    public void endFragment() throws MQLException {
        super.endElement("fragment");
    }

    @Override
    public void fragmentContent(String content) throws MQLException {
        super.dataElement("content", content);
    }

    @Override
    public void qname(int kind, final String prefix, final String localPart) throws MQLException {
        super.emptyElement("qname", new String[][]{{"prefix", prefix}, {"localpart", localPart}});
    }

    @Override
    public void startName() throws MQLException {
        super.startElement("name");
    }

    @Override
    public void endName() throws MQLException {
        super.endElement("name");
    }

    @Override
    public void startOccurrencePredicate() throws MQLException {
        super.startElement("occurrence-predicate");
    }

    @Override
    public void endOccurrencePredicate() throws MQLException {
        super.endElement("occurrence-predicate");
    }

    @Override
    public void startPredicate() throws MQLException {
        super.startElement("predicate");
    }

    @Override
    public void endPredicate() throws MQLException {
        super.endElement("predicate");
    }

    @Override
    public void startPair() throws MQLException {
        super.startElement("pair");
    }

    @Override
    public void endPair() throws MQLException {
        super.endElement("pair");
    }

    @Override
    public void startType() throws MQLException {
        super.startElement("type");
    }

    @Override
    public void endType() throws MQLException {
        super.endElement("type");
    }

    @Override
    public void startPlayer() throws MQLException {
        super.startElement("player");
    }

    @Override
    public void endPlayer() throws MQLException {
        super.endElement("player");
    }

    @Override
    public void subjectIdentifier(String iri) throws MQLException {
        super.emptyElement("subject-identifier", "value", iri);
    }

    @Override
    public void subjectLocator(String iri) throws MQLException {
        super.emptyElement("subject-locator", "value", iri);
    }

    @Override
    public void itemIdentifier(String iri) throws MQLException {
        super.emptyElement("item-identifier", "value", iri);
    }

    @Override
    public void objectId(String ident) throws MQLException {
        super.emptyElement("object-id", "value", ident);
    }

    @Override
    public void string(String val) throws MQLException {
        super.emptyElement("string", "value", val);
    }

    @Override
    public void parameter(String name) throws MQLException {
        super.emptyElement("string", "name", name);
    }

    @Override
    public void iri(String iri) throws MQLException {
        super.emptyElement("iri", "value", iri);
    }

    @Override
    public void integer(Integer val) throws MQLException {
        super.emptyElement("integer", "value", val.toString());
    }

    @Override
    public void decimal(Float val) throws MQLException {
        super.emptyElement("decimal", "value", val.toString());
    }

}
