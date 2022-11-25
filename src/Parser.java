import java.util.ArrayList;
import java.util.Scanner;


public class Parser
{
    private static final String[] validCommands = {
            "swap", "quit", "help", "put", "skip"
    };
    private Scanner reader;         // source of command input

    /* Create a parser to read from the terminal window.
     */
    public Parser()
    {
        reader = new Scanner(System.in);
    }
    /* Get the command from the user.
     *
     * @return The next command from the user
     */
    public Command getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;


        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }



        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }
    public String getCommands()
    {
        StringBuilder s = new StringBuilder();
        for(String command: validCommands) {
            s.append(command + "  ");
        }
        String str = s.toString();
        return str.trim(); // removes spaces from beginning/end
    }

    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    public String[] parsePieceInfo(String pieceInfo){
        String[] arrOfStr = pieceInfo.split("");
        return arrOfStr;
    }

    public ArrayList<SelectionData> parsePieceData(String pieceInfo){
        ArrayList<SelectionData> pieceData = new ArrayList<SelectionData>();
        String[] parsedInputWord = parseInputWord(pieceInfo);
        for (int i = 0 ; i < parsedInputWord.length; i++){
            SelectionData d = new SelectionData(parsedInputWord[i]);
            pieceData.add(d);
            System.out.println(pieceData.get(i).getPiece().getLetter() + " " + pieceData.get(i).getX() + " " + pieceData.get(i).getY());
        }
        return pieceData;
    }

    public String[]  parseInputWord(String InputWord){
        String[] arrLstOfStr = InputWord.split(",");
        return arrLstOfStr;
    }


}
