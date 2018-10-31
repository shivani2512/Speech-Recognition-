package voiceassistant;

//Imports
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
 
/**
 *
 * @author Harsh and Shivani
 */
public class VoiceAssistant {
    
    private LiveSpeechRecognizer recognizer;
    
    boolean active=false;
    String work = null;
    Process p;
    
    public void VoiceAssistant() {
        
        //Configuration Object
        Configuration configuration = new Configuration();
 
        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("file:///D:\\Project\\VoiceAssistant\\assets\\3017.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("file:///D:\\Project\\VoiceAssistant\\assets\\3017.lm");
        
        /*Set grammer
        configuration.setGrammarPath("resource:/grammars");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);*/
        
        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException e) {
            System.out.println(e);
        }
         
        //Start speech recognition 
        startSpeechRecognition();
    }
    
    public void startSpeechRecognition() {
        
        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognizer.startRecognition(true);
        
        //Creating SpeechResult object
        SpeechResult result;
 
        //Check if recognizer recognized the speech
        while ((result = recognizer.getResult()) != null) {
 
            //Get the recognized speech
            String command = result.getHypothesis();
            
            //System.out.println(result.getHypothesis());
            
            if (command.equalsIgnoreCase("Wake Up")) {
                active=true;
                System.out.println("I am ready to help");
                speak("I am ready to help");
            } else if(command.equalsIgnoreCase("Sleep")) {
                active=false;
                System.out.println("I am sleepy! Bye");
                speak("I am sleepy! Bye");
            }
            
            if(active==true) {
                if(command.equalsIgnoreCase("who are you")) {
                    intro();
                } else if(command.equalsIgnoreCase("good morning")) {
                    greetings();
                } else if (command.equalsIgnoreCase("what is the current time")) {
                    getCurrentTime();
                } else if (command.equalsIgnoreCase("open browser")) {
                    openBrowsere();
                } else if (command.equalsIgnoreCase("close browser")) {
                    closeBrowsere();
                } else if (command.equalsIgnoreCase("open word")) {
                    openWord();
                } else if (command.equalsIgnoreCase("close word")) {
                    closeWord();
                } else if (command.equalsIgnoreCase("open excel")) {
                    openExcel();
                } else if (command.equalsIgnoreCase("close excel")) {
                    closeExcel();
                } else if (command.equalsIgnoreCase("open power point")) {
                    openPowerPoint();
                } else if (command.equalsIgnoreCase("close power point")) {
                    closePowerPoint();
                } else if (command.equalsIgnoreCase("open paint")) {
                    openPaint();
                } else if (command.equalsIgnoreCase("close paint")) {
                    closePaint();
                } else if (command.equalsIgnoreCase("open notepad")) {
                    openNotePad();
                } else if (command.equalsIgnoreCase("close notepad")) {
                    closeNotePad();
                } else if (command.equalsIgnoreCase("open command prompt")) {
                    openCMD();
                } else if (command.equalsIgnoreCase("close command prompt")) {
                    closeCMD();
                } else if (command.equalsIgnoreCase("open open control panel")) {
                    openCP();
                } else if (command.equalsIgnoreCase("close open control panel")) {
                    closeCP();
                } else if (command.equalsIgnoreCase("open task manager")) {
                    openTaskManager();
                } else if (command.equalsIgnoreCase("close task manager")) {
                    closeTaskManager();
                } else if (command.equalsIgnoreCase("open calculator")) {
                    openCal();
                } else if (command.equalsIgnoreCase("close calculator")) {
                    closeCal();
                } else if (command.equalsIgnoreCase("open player")) {
                    openPlayer();
                } else if (command.equalsIgnoreCase("close player")) {
                    closePlayer();
                } else if (command.equalsIgnoreCase("save file")) {
                    saveFile();
                } else if (command.equalsIgnoreCase("copy file")) {
                    copyFile();
                } else if (command.equalsIgnoreCase("cut file")) {
                    cutFile();
                } else if (command.equalsIgnoreCase("paste file")) {
                    pasteFile();
                } else if (command.equalsIgnoreCase("undo")) {
                    undo();
                } else if (command.equalsIgnoreCase("redo")) {
                    redo();
                } else {
                    //System.out.println("Sorry, i didn't get that");
                    work = null;
                }
                
                //In case command recognized is none of the above hence work might be null
                if(work != null) {
                    //Execute the command
                    try {
                        p = Runtime.getRuntime().exec(work);
                    } catch(IOException e){
                        System.out.println(e);
                    }
                } 
            }  
        }
    }
    
    //Operations
    public void intro() {
        System.out.println("I am your assistant, i am here to help you");
        speak("I am your assistant, i am here to help you");
    }
    
    public void greetings() {
        System.out.println("Good Morning. How can i help?");
        speak("Good Morning. How can i help?");
    }
    
    public void getCurrentTime() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("The time is "+String.valueOf(df.format(date)));
        speak("The time is "+String.valueOf(df.format(date)));
    }
    
    public void openBrowsere() {
        System.out.println("Opening browser");
        speak("Opening browser");
        work="cmd /c start chrome.exe";
    }
    public void closeBrowsere() {
        System.out.println("Closing browser");
        speak("Closing browser");
        work="cmd /c start taskkill /im chrome.exe /f";
    }
    
    public void openWord(){
        System.out.println("Opening word");
        speak("Opening word");
        work="cmd /c start winword";
    }
    public void closeWord(){
        System.out.println("Closing word");
        speak("Closing word");
        work="cmd /c start taskkill /im winword.exe /f";
    }
    
    public void openExcel(){
        System.out.println("Opening excel");
        speak("Opening excel");
        work="cmd /c start excel";
    }
    public void closeExcel(){
        System.out.println("Closing excel");
        speak("Closing excel");
        work="cmd /c start taskkill /im excel.exe /f";
    }
    
    public void openPowerPoint(){
        System.out.println("Opening power point");
        speak("Opening power point");
        work="cmd /c start powerpnt";
    }
    public void closePowerPoint(){
        System.out.println("Closing power point");
        speak("Closing power point");
        work="cmd /c start taskkill /im powerpnt.exe /f";
    }
    
    public void openPaint(){
        System.out.println("Opening paint");
        speak("Opening paint");
        work="cmd /c start mspaint";
    }
    public void closePaint(){
        System.out.println("Closing paint");
        speak("Closing paint");
        work="cmd /c start taskkill /im mspaint.exe /f";
    }
    
    public void openNotePad(){
        System.out.println("Opening notepad");
        speak("Opening notepad");
        work="cmd /c start notepad";
    }
    public void closeNotePad(){
        System.out.println("Closing notepad");
        speak("Closing notepad");
        work="cmd /c start taskkill /im notepad.exe /f";
    }
    
    public void openCMD(){
        System.out.println("Opening command prompt");
        speak("Opening command prompt");
        work="cmd /c start cmd";
    }
    public void closeCMD(){
        System.out.println("Closing command prompt");
        speak("Closing command prompt");
        work="cmd /c start taskkill /im cmd.exe /f";
    }
    
    public void openCP(){
        System.out.println("Opening control panel");
        speak("Opening control panel");
        work="cmd /c start control";
    }
    public void closeCP(){
        System.out.println("Closing control panel");
        speak("Closing control panel");
        work="cmd /c start taskkill /im control.exe /f";
    }
    
    public void openTaskManager(){
        System.out.println("Opening task manager");
        speak("Opening task manager");
        work="cmd /c start taskmgr";
    }
    public void closeTaskManager(){
        System.out.println("Closing task manager");
        speak("Closing task manager");
        work="cmd /c start taskkill /im taskmgr.exe /f";
    }
    
    public void openCal(){
        System.out.println("Opening calculator");
        speak("Opening calculator");
        work="cmd /c start calc";
    }
    public void closeCal(){
        System.out.println("Closing calculator");
        speak("Closing calculator");
        work="cmd /c start taskkill /im calculator.exe /f";
    }
    
    public void openPlayer(){
        System.out.println("Opening media player");
        speak("Opening media player");
        work="cmd /c start wmplayer";
    }
    public void closePlayer(){
        System.out.println("Closing media player");
        speak("Closing media player");
        work="cmd /c start taskkill /im wmplayer.exe /f";
    }
    
    public void saveFile(){
        try {
            System.out.println("Saving your file");
            speak("Saving your file");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    public void copyFile(){
        try {
            System.out.println("Copying");
            speak("Copying");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_C);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    public void cutFile(){
        try {
            System.out.println("Cuting");
            speak("Cuting");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_X);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    public void pasteFile(){
        try {
            System.out.println("Pasting");
            speak("Pasting");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    public void undo(){
        try {
            System.out.println("Undoing");
            speak("Undoing");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_Z);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    public void redo(){
        try {
            System.out.println("Redoing");
            speak("Redoing");
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_Y);
        } catch (AWTException e) {
            System.out.println(e);
        }
    }
    
    
    
    //voice function
    public void speak(String speechText) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice("kevin16");
        voice.allocate();
        voice.speak(speechText);
    }
    
    public static void main(String[] args) throws IOException {
        VoiceAssistant va = new VoiceAssistant();
        va.VoiceAssistant();
    }
    
}