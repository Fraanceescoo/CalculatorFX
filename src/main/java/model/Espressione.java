package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class Espressione {
    private static ArrayList<Object> tokensExpr = new ArrayList<>();
    private static ArrayList<Object> rpnExpr = new ArrayList<>();

    private static void scanner(String inputExpr) throws EspressioneException {
        long numero = 0;
        boolean inLetturaNumero = false;
        for (char carattere : inputExpr.toCharArray()) {
            if (!inLetturaNumero) {
                switch (carattere) {
                    case '(':
                        tokensExpr.add(Parentesi.PARENTESI_APERTA);
                        break;
                    case ')':
                        tokensExpr.add(Parentesi.PARENTESI_CHIUSA);
                        break;
                    case '^':
                        tokensExpr.add(Operatore.POW);
                        break;
                    case '+':
                        tokensExpr.add(Operatore.ADD);
                        break;
                    case '-':
                        tokensExpr.add(Operatore.SUB);
                        break;
                    case '*':
                        tokensExpr.add(Operatore.MULT);
                        break;
                    case '/':
                        tokensExpr.add(Operatore.DIV);
                        break;
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                        numero = Integer.valueOf(Character.toString(carattere));
                        inLetturaNumero = true;
                        break;
                    default:
//                        throw new EspressioneException("Carattere non valido");

                }
            } else {
                switch (carattere) {
                    case '(':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Parentesi.PARENTESI_APERTA);
                        break;
                    case ')':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Parentesi.PARENTESI_CHIUSA);
                        break;
                    case '^':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Operatore.POW);
                        break;
                    case '+':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Operatore.ADD);
                        break;
                    case '-':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Operatore.SUB);
                        break;
                    case '*':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Operatore.MULT);
                        break;
                    case '/':
                        inLetturaNumero = false;
                        tokensExpr.add(new Frazione(numero, 1));
                        tokensExpr.add(Operatore.DIV);
                        break;
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                        numero *= 10;
                        numero += Integer.valueOf(Character.toString(carattere));
                        inLetturaNumero = true;
                    default:
//                        throw new EspressioneException("Espressione non valida");
                }
            }
        }
        if (inLetturaNumero)
            tokensExpr.add(new Frazione(numero, 1));
    }

    private static Frazione calcRPN() {
        ArrayDeque<Frazione> stackOperandi = new ArrayDeque<>();
        Frazione operando1, operando2, risultatoParziale = null;
        for (Object token : rpnExpr) {
            if (token instanceof Frazione) {
                stackOperandi.push((Frazione) token);
            } else {
                operando2 = stackOperandi.pop();
                operando1 = stackOperandi.pop();
                try {
                    switch ((Operatore) token) {
                        case Operatore.ADD:
                            risultatoParziale = operando1.add(operando2);
                            break;
                        case Operatore.SUB:
                            risultatoParziale = operando1.sub(operando2);
                            break;
                        case Operatore.DIV:
                            risultatoParziale = operando1.div(operando2);
                            break;
                        case Operatore.MULT:
                            risultatoParziale = operando1.mult(operando2);
                            break;
                        case Operatore.POW:
                            risultatoParziale = operando1.pow(operando2);
                            break;
                    }
                } catch (ArithmeticException errore) {
                    throw new ArithmeticException("errore operatore");
                }
                stackOperandi.push(risultatoParziale);
            }
        }
        return stackOperandi.pop();
    }

    private static void shuntingYard() throws EspressioneException {
        Stack<Object> opStack = new Stack<>();
        for (Object token : tokensExpr) {
            if (token instanceof Frazione) {
                rpnExpr.add(token);
            } else if (token instanceof Operatore op) {
                while (!opStack.isEmpty() && opStack.peek() instanceof Operatore op1
                        && op1.getValore() >= op.getValore()) {
                    rpnExpr.add(opStack.pop());
                }
                opStack.push(op);
            } else if (token.equals(Parentesi.PARENTESI_APERTA)) {
                opStack.push(token);
            } else if (token.equals(Parentesi.PARENTESI_CHIUSA)) {
                while (!opStack.isEmpty() && !opStack.peek().equals(Parentesi.PARENTESI_APERTA)) {
                    rpnExpr.add(opStack.pop());
                }
                if (opStack.isEmpty()) {
                    throw new EspressioneException("errore parentesi");
                }
                opStack.pop();
            }
        }
        while (!opStack.isEmpty()) {
            Object obj = opStack.pop();
            if (obj instanceof Parentesi) {
                throw new EspressioneException("Errore");
            }
            rpnExpr.add(obj);
        }
    }

    public static Frazione calculate(String inputExpr) throws EspressioneException {
        tokensExpr.clear();
        rpnExpr.clear();
        scanner(inputExpr);
        shuntingYard();
        return calcRPN();
    }
}
