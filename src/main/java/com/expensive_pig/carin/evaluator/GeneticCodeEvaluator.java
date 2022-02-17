package com.expensive_pig.carin.evaluator;

import com.expensive_pig.carin.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneticCodeEvaluator {
    private static final int MAX_RAND_BOUND = 100;
    private static final int MAX_ITERATION = 1000;
    private final Random rand = new Random();

    private Entity host;
    private final Map<String, Integer> variableMap = new HashMap<>();
    private boolean isActionPerformed = false;

    private int loopCounter = 0;

    private StringBuilder commandsCall;

    public String evaluateProgram(Program program, Entity host) throws SyntaxError {
        commandsCall = new StringBuilder();
        this.host = host;
        isActionPerformed = false;
        program.resetIterator();
        while (program.hasNext()) {
            loopCounter = 0;
            evalStatement(program.getNextStatement());
        }
        return commandsCall.toString();
    }

    private int evalStatement(Statement statement) throws SyntaxError {
        if (statement == null || loopCounter > MAX_ITERATION) return 0;

        if (statement instanceof IfStatement ifStatement) {
            // If statement
            if (evalStatement(ifStatement.getExpression()) > 0) {
                evalStatement(ifStatement.getStatementIfTrue());
            } else {
                evalStatement(ifStatement.getStatementIfFalse());
            }

        } else if (statement instanceof BlockStatement) {
            // Block statement
            for (Statement s : ((BlockStatement) statement).list_val()) {
                evalStatement(s);
            }

        } else if (statement instanceof WhileStatement whileStatement) {
            // While statement
            while (evalStatement(whileStatement.getExpression()) > 0) {
                evalStatement(whileStatement.getStatementIfTrue());
                loopCounter++;
            }

        } else if (statement instanceof AssignStatement assignStatement) {
            // Assignment statement
            String identifier = assignStatement.getIdentifier().string_val();
            if (!identifier.equals("random")) {
                if (!variableMap.containsKey(identifier)) {
                    variableMap.put(identifier, 0);
                }
                int val = evalStatement(assignStatement.getExpression());
                variableMap.put(identifier, val);
            }
        } else if (statement instanceof ActionCommand actionCommand) {
            // Action Command
            if (!isActionPerformed) {
                System.out.println(actionCommand.string_val());
                commandsCall.append(actionCommand.string_val()).append("\n");
                // todo
                if (actionCommand.getActionCmd().equals("move")) {

                     host.move(actionCommand.getDirection());

                } else if (actionCommand.getActionCmd().equals("shoot")) {

                    host.shoot(actionCommand.getDirection());

                } else {
                    throw new SyntaxError();
                }
                isActionPerformed = true;
            }

        } else if (statement instanceof Expr expr) {
            // Expression
            int lv = evalStatement(expr.getLeft());
            int rv = evalStatement(expr.getRight());

            return switch (expr.getOp()) {
                case "+" -> lv + rv;
                case "-" -> lv - rv;
                case "*" -> lv * rv;
                case "/" -> lv / rv;
                case "%" -> lv % rv;
                case "^" -> (int) Math.pow(lv, rv);
                default -> throw new SyntaxError();
            };

        } else if (statement instanceof Identifier identifier) {
            // Identifier, get variable, or get random
            String varName = identifier.string_val();
            if (varName.equals("random")) {
                return rand.nextInt(MAX_RAND_BOUND);
            } else if(variableMap.containsKey(varName)){
                return variableMap.get(varName);
            } else {
                throw new SyntaxError();
            }

        } else if (statement instanceof SensorExpr sensorExpr) {
            // Sensor Command
            commandsCall.append(sensorExpr.string_val()).append("\n");
            // todo
            return switch (sensorExpr.getCommand()) {
                case "virus" -> host.getVirus();
                case "antibody" -> host.getAntibody();
                case "nearby" -> host.nearby(sensorExpr.getDirection());
                default -> throw new SyntaxError();
            };
        } else if (statement instanceof IntLiteral intLit) {
            return intLit.eval();
        }

        return 0;
    }
}
