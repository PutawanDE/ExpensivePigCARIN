package com.expensive_pig.carin.evaluator;

import com.expensive_pig.carin.core.Direction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Parser_Expr {
    private Tokenizer token;
    private Program program;
    private static final Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "down", "downleft", "downright", "left", "right",
            "up", "upleft", "upright", "virus", "antibody",
            "while", "else", "if", "shoot", "then", "move", "nearby"
    ));


    /**
     * Program → Statement+
     * Statement → Command | BlockStatement | IfStatement | WhileStatement
     * Command → AssignmentStatement | ActionCommand
     * AssignmentStatement → <identifier> = Expression
     * ActionCommand → MoveCommand | AttackCommand
     * MoveCommand → move Direction
     * AttackCommand → shoot Direction
     * Direction → left | right | up | down | upleft | upright | downleft | downright
     * BlockStatement → { Statement* }
     * IfStatement → if ( Expression ) then Statement else Statement
     * WhileStatement → while ( Expression ) Statement
     * Expression → Expression + Term | Expression - Term | Term
     * Term → Term * Factor | Term / Factor | Term % Factor | Factor
     * Factor → Power ^ Factor | Power
     * Power → <number> | <identifier> | ( Expression ) | SensorExpression
     * SensorExpression → virus | antibody | nearby Direction
     *
     * @throws SyntaxError
     */

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Program parse(String stream) throws SyntaxError {
        this.token = new Tokenizer();
        token.cutter(stream);
        program = parseProgram();
        return program;
    }

    //==================================================================

    /**
     * Program → Statement+
     */
    private Program parseProgram() throws SyntaxError {
        Program program = new Program();
        while (!token.empty()) {
            program.addStatement(parseStatement());
        }
        return program;
    }

    /**
     * Statement → Command | BlockStatement | ifStatement | WhileStatement
     */
    private Statement parseStatement() throws SyntaxError {
        String this_peek = token.peek();
        return switch (this_peek) {
            case "{" -> parseBlockStatement();          //BlockStatement
            case "if" -> parseIfStatement();            //ifStatement
            case "while" -> parseWhileStatement();      // WhileStatement
            default -> parseCommand();                  //Command
        };
    }

    /**
     * Command → AssignmentStatement | ActionCommand
     */
    private Statement parseCommand() throws SyntaxError {
        String this_peek = token.peek();
        // token.consume();
        if (this_peek.equals("move") | this_peek.equals("shoot")) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

    /**
     * AssignmentStatement → <identifier> = Expression
     */
    private Statement parseAssignmentStatement() throws SyntaxError {
        Statement identifier = parseIdentifier();
        token.consume_check("=");
        Statement expression = parseExpression();
        return new AssignStatement(identifier, "=", expression);
    }

    /**
     * ActionCommand → MoveCommand | AttackCommand
     */
    private Statement parseActionCommand() throws SyntaxError {
        String this_peek = token.peek();
        if (this_peek.equals("move")) {
            return parseMoveCommand();
        } else if (this_peek.equals("shoot")) {
            return parseAttackCommand();
        } else {
            throw new SyntaxError();
        }
    }

    /**
     * MoveCommand → move Direction
     */
    private Statement parseMoveCommand() throws SyntaxError {
        String this_peek = token.peek();
        token.consume_check("move");
        if (this_peek.equals("move")) {
            return new ActionCommand("move", parseDirection());
        } else throw new SyntaxError();
    }

    /**
     * AttackCommand → shoot Direction
     */
    private Statement parseAttackCommand() throws SyntaxError {
        String this_peek = token.peek();
        token.consume_check("shoot");
        if (this_peek.equals("shoot")) {
            return new ActionCommand("shoot", parseDirection());
        } else throw new SyntaxError();
    }

    /**
     * Expression → Expression + Term | Expression - Term | Term
     * so use reassociation
     * Expression → Term( + Term)* | Term( - Term)* | Term
     */
    private Statement parseExpression() throws SyntaxError {
        Statement term = parseTerm();
        while (token.peek_check("+") || token.peek_check("-")) {
            String this_peek = token.peek();
            token.consume(); // remove "+" or "-"
            switch (this_peek) {
                case "+" -> term = new Expr(term, "+", parseTerm());
                case "-" -> term = new Expr(term, "-", parseTerm());
                default -> throw new SyntaxError();
            }
        }
        return term;
    }

    /**
     * Term → Term * Factor | Term / Factor | Term % Factor | Factor
     * so use reassociation
     * Term → Factor (* Factor)* | Factor (/ Factor)* | Factor (% Factor)* | Factor
     */
    private Statement parseTerm() throws SyntaxError {
        Statement factor = parseFactor();
        while (token.peek_check("*") || token.peek_check("/") || token.peek_check("%")) {
            String this_peek = token.peek();
            token.consume(); // remove "*" or "/" or "%"
            switch (this_peek) {
                case "*" -> factor = new Expr(factor, "*", parseFactor());
                case "/" -> factor = new Expr(factor, "/", parseFactor());
                case "%" -> factor = new Expr(factor, "%", parseFactor());
                default -> throw new SyntaxError();
            }
        }
        return factor;
    }

    /**
     * Factor → Power ^ Factor |   Power
     */
    private Statement parseFactor() throws SyntaxError {
        Statement power = parsePower();
        if (token.peek_check("^")) {
            String this_peek = token.peek();
            if (this_peek.equals("^")) {
                token.consume_check("^");
                power = new Expr(power, "^", parseFactor());
            }
        }
        return power;

    }

    /**
     * Power → <number> | <identifier> |  Expression | SensorExpression
     */
    private Statement parsePower() throws SyntaxError {
        //parseInt
        String this_peek = token.peek();
        if (isNumeric(this_peek)) {
            return new IntLiteral(Integer.parseInt(token.consume()));
        } else if (this_peek.equals("(")) {
            token.consume_check("(");
            Statement expression = parseExpression();
            token.consume_check(")");
            return expression;
        } else if (this_peek.equals("virus") || this_peek.equals("antibody") || this_peek.equals("nearby")) {
            return parseSensorExpression();
        } else {
            return parseIdentifier();
        }
    }

    /**
     * identifier is valuable
     */
    private Statement parseIdentifier() throws SyntaxError {
        String this_peek = token.peek();
        token.consume(); //
        if (reservedWords.contains(this_peek)) {
            throw new SyntaxError();
        }
        if (!isNumeric("" + this_peek.charAt(0))) {
            // If after this_peak[0] are alphanumeric
            if (this_peek.substring(1).chars().allMatch(Character::isLetterOrDigit)) {
                return new Identifier(this_peek);
            }
        }
        throw new SyntaxError();
    }

    /**
     * SensorExpression → virus | antibody |  nearby_Direction
     */
    private Statement parseSensorExpression() throws SyntaxError {
        String this_peek = token.peek();
        token.consume();
        switch (this_peek) {
            case "virus":
                return new SensorExpr("virus");
            case "antibody":
                return new SensorExpr("antibody");
            case "nearby":
                Direction direction = parseDirection();
                return new SensorExpr("nearby", direction);
            default:
                throw new SyntaxError();
        }
    }

    /**
     * Direction → return direction
     * down, downleft, downright,   left,   right, shoot, then, up, upleft, upright
     */
    private Direction parseDirection() throws SyntaxError {
        String this_peek = token.peek();
        token.consume(); // remove down, downleft, downright,   left,   right, shoot, then, up, upleft, upright
        Direction direction = Direction.getDirectionByString(this_peek);
        if (direction != null) {
            return direction;
        } else {
            throw new SyntaxError();
        }
    }

    /**
     *  BlockStatement
     */
    /**
     * BlockStatement → { Statement* }
     */
    private BlockStatement parseBlockStatement() throws SyntaxError {
        token.consume_check("{");

        LinkedList<Statement> prossed = new LinkedList<>();
        while (!token.peek_check("}")) {
            prossed.add(parseStatement());
        }

        token.consume_check("}");
        return new BlockStatement(prossed);
    }

    /**
     *    ifStatement
     */
    /**
     * ifStatement → if (Expression) then statement  else statement
     */
    private Statement parseIfStatement() throws SyntaxError {
        token.consume_check("if");
        token.consume_check("(");
        Statement Expression = parseExpression();
        token.consume_check(")");
        token.consume_check("then");
        Statement true_statement = parseStatement();
        token.consume_check("else");
        Statement false_statement = parseStatement();

        return new IfStatement(Expression, true_statement, false_statement);

    }

    /**
     *   WhileStatement
     */
    /**
     * WhileStatement → while ( Expression ) Statement
     */
    private Statement parseWhileStatement() throws SyntaxError {
        token.consume_check("while");
        Statement Expression = parseExpression();

        Statement true_statement = parseStatement();

        return new WhileStatement(Expression, true_statement);
    }
}
