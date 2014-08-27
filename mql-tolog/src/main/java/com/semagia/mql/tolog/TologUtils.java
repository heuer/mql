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
 * Utility functions to determine built-in predicates etc.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
final class TologUtils {

    private static final String _TOLOG_STRING_MODULE = "http://psi.ontopia.net/tolog/string/";
    private static final String _TOLOG_EXPERIMENTAL_MODULE = "http://psi.ontopia.net/tolog/experimental/";

    private TologUtils() {
        // noop.
    }

    /**
     * Returns if the provided name represents a built-in predicate.
     *
     * @param name The name to check.
     * @return {@code true} if the provided name is a built-in predicate, 
     *          otherwise {@code false}.
     */
    public static boolean isBuiltinPredicate(final String name) {
        return _isInfixPredicate(name)
                || "association".equals(name)
                || "association-role".equals(name)
                || "base-locator".equals(name)
                || "datatype".equals(name)
                || "direct-instance-of".equals(name)
                || "instance-of".equals(name)
                || "item-identifier".equals(name)
                || "object-id".equals(name)
                || "occurrence".equals(name)
                || "reifies".equals(name)
                || "resource".equals(name)
                || "role-player".equals(name)
                || "scope".equals(name)
                || "subject-identifier".equals(name)
                || "subject-locator".equals(name)
                || "topic".equals(name)
                || "topic-name".equals(name)
                || "topicmap".equals(name)
                || "type".equals(name)
                || "value".equals(name)
                || "value-like".equals(name)
                || "variant".equals(name)
                // tolog 1.2
                || "coalesce".equals(name) 
                // didn't make it into 1.2, c.f. experimental module
                // || "name".equals(name)
                // deprecated
                || "source-locator".equals(name)
                ;
    }

    /**
     * Returns if the provided name represents an infix predicate
     *
     * @param name The name to check.
     * @return {@code true} if the provided name is an infix predicate, 
     *          otherwise {@code false}.
     */
    private static boolean _isInfixPredicate(final String name) {
        return "/=".equals(name)
                || "<".equals(name)
                || "<=".equals(name)
                || "=".equals(name)
                || ">".equals(name)
                || ">=".equals(name);
    }

    /**
     * Return if the provided {@code name} is a built-in function or predicate
     * for the provided module.
     * 
     * @param name The function name.
     * @param iri The IRI of the module.
     * @return {@code true} if the provided name is a built-in function, 
     *          otherwise {@code false}.
     */
    public static boolean isBuiltinFunction(final String name, final String iri) {
        if (iri == null) {
            return _isBuiltinFunction(name);
        }
        else if (_TOLOG_STRING_MODULE.equals(iri)) {
            return _isStringFunction(name);
        }
        else if (_TOLOG_EXPERIMENTAL_MODULE.equals(iri)) {
            return _isExperimentalFunction(name);
        }
        return false;
    }

    /**
     * Returns if the provided function name is a string function.
     *
     * @param name The name to check.
     * @return {@code true} if the provided name is a string function, 
     *          otherwise {@code false}.
     */
    private static boolean _isStringFunction(final String name) {
        return "concat".equals(name)
                || "contains".equals(name)
                || "ends-with".equals(name)
                || "index-of".equals(name)
                || "last-index-of".equals(name)
                || "length".equals(name)
                || "starts-with".equals(name)
                || "substring".equals(name)
                || "substring-after".equals(name)
                || "substring-before".equals(name)
                || "translate".equals(name);
    }

    /**
     * Shortcut for {@link #isBuiltinFunction(String, null)}.
     *
     * @param name The function name.
     * @return {@code true} if the provided name is a built-in function, 
     *          otherwise {@code false}.
     */
    private static boolean _isBuiltinFunction(final String name) {
        return _isUpdateFunction(name) || _isDeleteFunction(name);
    }

    /**
     * Returns if the provided name represents a update function in tolog 1.2.
     *
     * @param name The function name.
     * @return {@code true} if the provided name is a update function, 
     *          otherwise {@code false}.
     */
    private static boolean _isUpdateFunction(final String name) {
        return "value".equals(name) || "resource".equals(name);
    }

    /**
     * Returns if the provided name represents a delete function in tolog 1.2.
     *
     * @param name The function name.
     * @return {@code true} if the provided name is a delete function, 
     *          otherwise {@code false}.
     */
    private static boolean _isDeleteFunction(final String name) {
        return "subject-identifier".equals(name)
                || "subject-locator".equals(name)
                || "item-identifier".equals(name)
                || "scope".equals(name)
                || "reifies".equals(name)
                || "direct-instance-of".equals(name);
    }

    /**
     * Returns if the provided function name is an experimental function.
     *
     * @param name The name to check.
     * @return {@code true} if the provided name is an experimental function, 
     *          otherwise {@code false}.
     */
    private static boolean _isExperimentalFunction(final String name) {
        return "in".equals(name)
                || "gt".equals(name)
                || "lt".equals(name)
                || "gteq".equals(name)
                || "lteq".equals(name)
                || "name".equals(name);
    }
}
