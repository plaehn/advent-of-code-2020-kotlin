package org.plaehn.adventofcode.day18

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser

class WrongPrecedenceArithmeticsEvaluator : Grammar<Long>() {

    @Suppress("unused")
    private val ws by regexToken("\\s+", ignore = true)

    private val num by regexToken("-?\\d+")
    private val lpar by literalToken("(")
    private val rpar by literalToken(")")
    private val mul by literalToken("*")
    private val plus by literalToken("+")

    private val number by num use { text.toLong() }
    private val term: Parser<Long> by number or
            (skip(lpar) and parser(this::rootParser) and skip(rpar))

    private val plusChain by leftAssociative(term, plus) { a, _, b -> a + b }

    private val mulChain by leftAssociative(plusChain, mul) { a, _, b -> a * b }

    override val rootParser: Parser<Long> by mulChain
}
