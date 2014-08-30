/**
 * 
 */
package com.semagia.mql.tolog;

import org.xml.sax.ContentHandler;

import com.semagia.mql.MQLException;
import com.semagia.mql.base.AbstractSAXEmittingQueryHandler;

/**
 * @author lars
 *
 */
public class SAXEmittingTologHandler extends AbstractSAXEmittingQueryHandler implements
        ITologHandler {

    private static final String TOLOG_NS = "http://psi.semagia.com/tolog-xml/";

    public SAXEmittingTologHandler(ContentHandler handler) {
        super(handler, TOLOG_NS);
    }

    @Override
    public void namespace(String ident, String iri, int kind)
            throws MQLException {
        // TODO Auto-generated method stub

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

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startName()
     */
    @Override
    public void startName() throws MQLException {
        super.startElement("name");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endName()
     */
    @Override
    public void endName() throws MQLException {
        super.endElement("name");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startDynamicPredicate()
     */
    @Override
    public void startDynamicPredicate() throws MQLException {
        super.startElement("dynamic-predicate");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endDynamicPredicate()
     */
    @Override
    public void endDynamicPredicate() throws MQLException {
        super.endElement("dynamic-predicate");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startPredicate()
     */
    @Override
    public void startPredicate() throws MQLException {
        super.startElement("predicate");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endPredicate()
     */
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

}
