public class Command {
    private String commandWord;
    private String inputWord;
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        inputWord = secondWord;
    }
    public String getCommandWord()
    {
        return commandWord;
    }
    public String getInputWord()
    {
        return inputWord;
    }
    public boolean isUnknown()
    {
        return (commandWord == null);
    }
    public boolean hasInputWord()
    {
        return (inputWord != null);
    }
}
