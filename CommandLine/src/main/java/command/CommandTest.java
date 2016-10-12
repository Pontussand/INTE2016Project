package command;

import java.util.Scanner;

public class CommandTest {
	public Scanner scan = new Scanner(System.in);
	private void run(){
		while(true){
			String command = scan.nextLine();
			command(command);
		}
	}
	public void command(String command){
		String commandPart = command.split(" ")[0];
		String target = command.split(" ")[command.indexOf(" ")];
		
		switch (commandPart) {

		case "1":
			System.out.println("Metod1" + target);
			break;
		case "2":
			System.out.println("Metod2" + target);
			break;
		default:
			System.out.println("Command doesnt exit");
		}
	}
	public static void main(String[] args){
		CommandTest test = new CommandTest();
		test.run();
	}
}
