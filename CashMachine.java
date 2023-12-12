package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.command.CommandExecutor;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class CashMachine {

    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation;
            do {
                CommandExecutor.execute(Operation.LOGIN);
                operation = ConsoleHelper.requestOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptedOperationException ignored) {
            ConsoleHelper.printExitMessage();
        }

//        ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.exit_en");
//
//        System.out.println(res.getString("exit.question.y.n"));

    }
}
