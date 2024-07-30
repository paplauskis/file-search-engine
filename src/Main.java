public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("--data")) {
            System.out.println("no file");
        } else {
            UserInterface ui = new UserInterface(args[1]);
            ui.start();
        }
    }
}