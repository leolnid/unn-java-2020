package expression.parser;

import expression.*;
import exceptions.*;

import java.util.Arrays;
import java.util.List;

public class ExpressionParser implements Parser {

    private final List<String> Variables = Arrays.asList("x", "y", "z", "tmp", "t1m2p3");
    private int balance;
    private String expression;
    private int value;
    private String variableName;
    private int index;
    private Token currentToken = Token.NULL;

    private enum Token {
        ADD, CLOSE_BRACE, DIV, END, LOG, LOG10, MUL, NULL,
        NUMBER, OPEN_BRACE, POW, POW10, SUB, UNARY_MINUS, VARIABLE
    }

    private void skipWhiteSpace() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private boolean isOperation(Token token) {
        return token == Token.ADD || token == Token.DIV || token == Token.LOG ||
                token == Token.MUL || token == Token.SUB || token == Token.POW;
    }

    private boolean isUnary(Token token) {
        return token == Token.CLOSE_BRACE || token == Token.NUMBER || token == Token.VARIABLE;
    }

    private void getNumber(String constant) throws IllegalConstantException {
        int start = index;
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            index++;
        }
        constant = constant.concat(expression.substring(start, index));
        try {
            value = Integer.parseInt(constant);
        } catch (NumberFormatException e) {
            throw new IllegalConstantException(constant, expression, index);
        }
        index--;
        currentToken = Token.NUMBER;
    }

    private String getSubString() {
        int start = index;
        while (index < expression.length() &&
                ((Character.isDigit(expression.charAt(index))) || (Character.isAlphabetic(expression.charAt(index))))) {
            index++;
        }
        index--;
        return expression.substring(start, index + 1);
    }

    private void nextToken() throws ParsingException {
        skipWhiteSpace();
        if (index >= expression.length()) {
            currentToken = Token.END;
            return;
        }
        char symbol = expression.charAt(index);
        switch (symbol) {
            case '+':
                currentToken = Token.ADD;
                break;
            case '*':
                if (expression.charAt(index + 1) == symbol) {
                    index++;
                    currentToken = Token.POW;
                } else {
                    currentToken = Token.MUL;
                }
                break;
            case '/':
                if (expression.charAt(index + 1) == symbol) {
                    index++;
                    currentToken = Token.LOG;
                } else {
                    currentToken = Token.DIV;
                }
                break;
            case '-':
                if (isUnary(currentToken)) {
                    currentToken = Token.SUB;
                } else {
                    index++;
                    skipWhiteSpace();
                    if (Character.isDigit(expression.charAt(index))) {
                        getNumber("-");
                    } else {
                        currentToken = Token.UNARY_MINUS;
                        index--;
                    }
                }
                break;
            case '(':
                currentToken = Token.OPEN_BRACE;
                balance++;
                break;
            case ')':
                currentToken = Token.CLOSE_BRACE;
                balance--;
                break;
            default:
                if (Character.isDigit(symbol)) {
                    getNumber("");
                } else if (Character.isAlphabetic(symbol)) {
                    String v = getSubString();
                    if (Variables.contains(v)) {
                        currentToken = Token.VARIABLE;
                        variableName = v;
                    } else if (v.equals("log10")) {
                        currentToken = Token.LOG10;
                    } else if (v.equals("pow10")) {
                        currentToken = Token.POW10;
                    } else {
                        currentToken = Token.NULL;
                        throw new UnknownSymbolException(expression, index);
                    }
                }
        }
        index++;
    }

    private TripleExpression unary() throws ParsingException {
        Token prevToken = currentToken;
        nextToken();
        if (balance < 0)
            throw new MissingOpeningParenthesisException(expression, index);
        TripleExpression result;
        switch (currentToken) {
            case LOG10:
                result = new CheckedLog(unary(), new Const(10));
                break;
            case NUMBER:
                result = new Const(value);
                nextToken();
                break;
            case OPEN_BRACE:
                result = addSub();
                nextToken();
                break;
            case POW10:
                result = new CheckedPow(new Const(10), unary());
                break;
            case VARIABLE:
                result = new Variable(variableName);
                nextToken();
                break;
            case UNARY_MINUS:
                result = new CheckedNegate(unary());
                break;
            default:
                String e = "";
                if (isOperation(prevToken) && isOperation(currentToken)) {
                    e = " middle";
                } else if (isOperation(prevToken) && !isOperation(currentToken)) {
                    e = " last";
                } else if (!isUnary(prevToken) && isOperation(currentToken)) {
                    e = " first";
                }
                throw new MissingOperandException(e, expression, index);
        }
        if (isUnary(prevToken) && isUnary(currentToken) && currentToken != Token.CLOSE_BRACE) {
            throw new MissingOperationException(expression + prevToken + currentToken, index);
        }
        if (balance < 0)
            throw new MissingOpeningParenthesisException(expression, index);
        return result;
    }

    private TripleExpression povLog() throws ParsingException {
        TripleExpression result = unary();
        while (true) {
            switch (currentToken) {
                case LOG:
                    result = new CheckedLog(result, unary());
                    break;
                case POW:
                    result = new CheckedPow(result, unary());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression mulDiv() throws ParsingException {
        TripleExpression result = povLog();
        while (true) {
            switch (currentToken) {
                case DIV:
                    result = new CheckedDivide(result, povLog());
                    break;
                case MUL:
                    result = new CheckedMultiply(result, povLog());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression addSub() throws ParsingException {
        TripleExpression result = mulDiv();
        while (true) {
            switch (currentToken) {
                case ADD:
                    result = new CheckedAdd(result, mulDiv());
                    break;
                case SUB:
                    result = new CheckedSubtract(result, mulDiv());
                    break;
                default:
                    return result;
            }
        }
    }

    public TripleExpression parse(String expression) throws ParsingException {
        this.expression = expression;
        balance = 0;
        currentToken = Token.NULL;
        index = 0;
        TripleExpression result = addSub();
        if (currentToken != Token.END)
            throw new MissingOperationException("", index);
        if (balance > 0)
            throw new MissingClosingParenthesisException(expression);
        return result;
    }
}