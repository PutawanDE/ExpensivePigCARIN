package com.expensive_pig.carin.evaluator;

import java.util.LinkedList;
import java.util.Objects;

public class Parser_Expr {
    private Tokenizer token;
    private String exp;
    private String text;
    private LinkedList prossed;

//    Tokenizer token = new Tokenizer();

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
    public Parser_Expr() throws SyntaxError {


    }

    public void parse(String stream) throws SyntaxError {
        this.exp = stream;
        build_ExprAST(exp);
    }


    public void build_ExprAST(String exp) throws SyntaxError {
        this.token = new Tokenizer();
        token.cutter(exp);
        parseProgram();
    }
    //==================================================================

    /**
     * Program → Statement+
     */
    void parseProgram() throws SyntaxError {
        LinkedList<Statement> prossed  = new LinkedList<>();
        while (!token.empty()){
            prossed.add(parseStatement());
        }
        this.prossed = prossed;

    }

    /**
     * Statement → Command | BlockStatement | ifStatement | WhileStatement
     */
//
    Statement parseStatement() throws SyntaxError {

        String this_peek = token.peek();
        if (Objects.equals(this_peek, "{")) {
            return parseBlockStatement();          //BlockStatement
        } else if (Objects.equals(this_peek, "if")) {      //ifStatement
            return parseIfStatement();
        } else if (Objects.equals(this_peek, "while")) {  // WhileStatement
            return parseWhileStatement();
        } else {                            //Command
            return parseCommand();
        }

    }

    /**
     *  Command
     */
    /**
     * Command → AssignmentStatement | ActionCommand
     */
    Statement parseCommand() throws SyntaxError {
        String this_peek = token.peek();
       // token.consume();
        if (this_peek.equals("move") | this_peek.equals("shoot") ) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

    /**
     * AssignmentStatement → <identifier> = Expression
     */
    Statement parseAssignmentStatement() throws SyntaxError {

        Statement identifier = parseIdentifier();

        token.consume_check("=");
        Statement expression = parseExpression() ;
        return new AssignExpr(identifier, "=", expression);
    }

    /**
     * ActionCommand → MoveCommand | AttackCommand
     */
    Statement parseActionCommand() throws SyntaxError {
        String this_peek = token.peek();
        if (this_peek.equals("move")) {
            return parseMoveCommand();
        } else if (this_peek.equals("shoot")) {
            return parseAttackCommand();
        } else {
//            return null;
            throw new SyntaxError();
        }
    }

    /**
     * MoveCommand → move Direction
     */
    Statement parseMoveCommand() throws SyntaxError {
        String this_peek = token.peek();
        token.consume_check("move");
            if (this_peek.equals("move")) {

                return  new ActionExpr("move", parseDirection());
            }
            else throw new SyntaxError();

    }

    /**
     * AttackCommand → shoot Direction
     */
    Statement parseAttackCommand() throws SyntaxError {
        String this_peek = token.peek();
        token.consume_check("shoot");
        if (this_peek.equals("shoot")) {

            return  new ActionExpr("shoot", parseDirection());
        }
        else throw new SyntaxError();

    }

    /**
     * Expression → Expression + Term | Expression - Term | Term
     * so use reassociation
     * Expression → Term( + Term)* | Term( - Term)* | Term
     */
    Statement parseExpression() throws SyntaxError {
        Statement term = parseTerm();
        while (token.peek_check("+") || token.peek_check("-")) {
            String this_peek = token.peek();
            token.consume(); // remove "+" or "-"
            switch (this_peek) {
                case "+" -> term = new TermExpr(term, "+", parseTerm());
                case "-" -> term = new TermExpr(term, "-", parseTerm());
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
    Statement parseTerm() throws SyntaxError {
        Statement factor = parseFactor();
        while (token.peek_check("*") || token.peek_check("/") || token.peek_check("%")) {
            String this_peek = token.peek();
            token.consume(); // remove "*" or "/" or "%"
            switch (this_peek) {
                case "*" -> factor = new TermExpr(factor, "*", parseFactor());
                case "/" -> factor = new TermExpr(factor, "/", parseFactor());
                case "%" -> factor = new TermExpr(factor, "%", parseFactor());
                default -> throw new SyntaxError();
            }
        }
        return factor;
    }

    /**
     * Factor → Power ^ Factor |   Power
     */
    Statement parseFactor() throws SyntaxError {
        Statement power = parsePower();
        String this_peek = token.peek();
        if (this_peek.equals("^")) {
            token.consume_check("^");
            return new TermExpr(power, "^", parseFactor());
        } else {
            return power;
        }
    }

    /**
     * Power → <number> | <identifier> |  Expression | SensorExpression
     */
    Statement parsePower() throws SyntaxError {
        //parseInt
        String this_peek = token.peek();
        if (isNumeric(this_peek)) {
            return new Parse_Data(token.consume());
        } else if(this_peek.equals("(")) {
            return parseExpression();
        } else if(this_peek.equals("virus") | this_peek.equals("antibody ")|this_peek.equals("nearby")){
            return parseSensorExpression();
        }else {
            return parseIdentifier();
        }

    }
    /**
     * identifier is valuable
     */
    Statement parseIdentifier() throws SyntaxError {

            String this_peek = token.peek();
            token.consume(); //


        return new Parse_Data(this_peek);

    }

    /**                                               i don't know
     * SensorExpression → virus | antibody |  nearby_Direction
     */
    Statement parseSensorExpression() throws SyntaxError {
        String this_peek = token.peek();
        if (this_peek.equals("virus")) {
            return new Parse_Data("virus");
        } else if (this_peek.equals("antibody")) {
            return new Parse_Data("antibody");
        } else if (this_peek.equals("nearby")) {
            return new ActionExpr("nearby", parseDirection());
        } else {
            throw new SyntaxError();
        }
    }




//       return switch (this_peek) {
//            case "virus" ->   new Parse_Data("virus");
//            case "antibody" ->    new Parse_Data("antibody");
//            case "nearby" ->   new ActionExpr("shoot", parseDirection());
//            default -> throw new SyntaxError();
//        };
//    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




    /**
     * Direction → return direction
     * down, downleft, downright,   left,   right, shoot, then, up, upleft, upright
     */
    Statement parseDirection() throws SyntaxError {
        String this_peek = token.peek();
        token.consume(); // remove down, downleft, downright,   left,   right, shoot, then, up, upleft, upright
        return switch (this_peek) {
            case "left" ->   new Parse_Data("left");
            case "right" ->   new Parse_Data("right");
            case "up" ->   new Parse_Data("up");
            case "down" ->   new Parse_Data("down");

            case "downleft" ->   new Parse_Data("downleft");
            case "downright" ->   new Parse_Data("downright");
            case "upleft" ->   new Parse_Data("upleft");
            case "upright" ->   new Parse_Data("upright");

            default -> throw new SyntaxError();
        };

    }

    /**
     *  BlockStatement
     */
    /**
     * BlockStatement → { Statement* }
     * @return
     */
    BlockExpr parseBlockStatement() throws SyntaxError {
        token.consume_check("{");

        LinkedList<Statement> prossed  = new LinkedList<>();
        while (!token.peek_check("}")){
            prossed.add(parseStatement());
        }

        token.consume_check("}");
        return  new BlockExpr(prossed);
    }

    /**
     *    ifStatement
     */
    /**
     * ifStatement → if (Expression) then statement  else statement
     */
    Statement parseIfStatement() throws SyntaxError {
        token.consume_check("if");
        token.consume_check("(");
        Statement Expression = parseExpression();
        token.consume_check(")");
        token.consume_check("then");
        Statement true_statement = parseStatement();
        token.consume_check("else");
        Statement false_statement = parseStatement();

        return new IfExpr(Expression, true_statement, false_statement);

    }

    /**
     *   WhileStatement
     */
    /**
     * WhileStatement → while ( Expression ) Statement
     */
    Statement parseWhileStatement() throws SyntaxError {
        token.consume_check("while");
        Statement Expression = parseExpression();

        Statement true_statement = parseStatement();

        return new WhileExpr(Expression, true_statement );
    }


    /**
     * T → T * F | T / F | T % F | F
     * so use reassociation
     * T → F (* F)* | F (/ F)* | F (% F)*
     */
//    statement parseT() throws SyntaxError {
//        statement v = parseF();
//        while (token.peek("*") || token.peek("/") || token.peek("%")) {
//            String this_peek = token.peek();
//            token.consume(); // remove * or / or %
//            switch (this_peek) {
//                case "*" -> v = new BinaryArithExpr(v, "*", parseF());
//                case "/" -> v = new BinaryArithExpr(v, "/", parseF());
//                case "%" -> v = new BinaryArithExpr(v, "%", parseF());
//                default -> throw new SyntaxError();
//            }
//        }
//        return v;
//    }

    /**
     * F → n | ( E )
     */
//    statement parseF() throws SyntaxError {
//        if (isNumeric(token.peek())) {
//            return new DoubleLit(Double.parseDouble(token.consume()));
//        } else {
//            token.consume("(");
//            statement v = parseE();
//            token.consume(")");
//            return v;
//        }
//    }

//
//    public static boolean isNumeric(String str) {
//        try {
//            Double.parseDouble(str);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
}
