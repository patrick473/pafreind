package Interface;

import controller.Interpreter.MainInterpreter;

import java.util.Scanner;

public class consoleInterface {

    public static void main(String[]args) {

        MainInterpreter mip = new MainInterpreter();

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        String userInput = "";

//once finished
        while (!userInput.equals("stop")){
            userInput = reader.nextLine();
            
            mip.interpret(userInput);

        }
        reader.close();
    }
}
