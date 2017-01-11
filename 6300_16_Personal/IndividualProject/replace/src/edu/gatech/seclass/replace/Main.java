package edu.gatech.seclass.replace;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    
    public static void main(String[] args) {
        
        ArrayList<String> notFoundFileList = new ArrayList<String>();
        
        // TODO: Empty skeleton method
        int sep_position = -1;
        int sep_count = 0;
        for (int p = 0; p < args.length; p++) {
            if ("--".compareTo(args[p]) == 0) {
                // find the last "--" position
                sep_count += 1;
                sep_position = p;
            }
        }
        if (sep_position == -1 || sep_count ==0) {
            usage();
            return;
        }
        
        // Command line:
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("f", false, "Change first word");
        options.addOption("l", false, "Change last word");
        options.addOption("i", false, "Case non-sensitive");
        options.addOption("b", false, "Save additional file after changes");
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);
            String[] inputArgs = cmd.getArgs();
            
            int start = 0;
            int temp = 0;
            
            if (inputArgs.length<3 ){
                usage();
                return;
            }
            
            for (int j = 0; j + 1 < args.length; ) {
                
                //if (args.length <=3){usage();return;
                if (args[0] == "--"){
                    temp = temp+1;
                    j=j+1;
                }
                
                if (inputArgs[0].compareTo(args[temp]) == 0) {
                    
                    String replace_from = args[j];
                    String replace_to = args[j + 1];
                    
                    if (j==0 && replace_from == "" ){usage();return;}
                    
                    if (j == sep_position || j + 1 == sep_position) {return;}
                    
                    for (int afterSep = 1; sep_position + afterSep < args.length; afterSep++) {
                        String target_file = args[sep_position + afterSep];
                        File target_fh = new File(target_file);
                        if (!target_fh.exists()) {
                            String notFoundFileName = target_fh.getName();
                            if (!notFoundFileList.contains(notFoundFileName)) {
                                notFoundFileList.add(notFoundFileName);
                                System.err.println("File " + notFoundFileName +" not found");
                            }
                            continue;
                        }
                        String target_text;
                        try {
                            target_text = readFile(target_file, Charset.defaultCharset());
                            String results = target_text;
                            
                            results = NormalR(results, replace_from, replace_to);
                            File output_file = new File(target_file);
                            FileWriter fileWriter = new FileWriter(output_file, false);
                            fileWriter.write(results);
                            fileWriter.close();
                            
                        } catch (IOException e) {
                            // do nothing
                        }
                        j=j+1;
                    }
                    
                } else if (inputArgs[start].compareTo(args[j]) == 0 && start<j) {
                    
                    if (j == sep_position || j + 1 == sep_position) {
                        return;
                    }
                    if (sep_count >= 2 && "--".compareTo(args[j]) == 0) {
                        j = j + 1;
                    }
                    
                    String replace_from = args[j];
                    String replace_to = args[j + 1];
                    start = start + 2;
                    
                    if (replace_from == "" ){usage();return;}
                    
                    for (int afterSep = 1; sep_position + afterSep < args.length; afterSep++) {
                        String target_file = args[sep_position + afterSep];
                        
                        File target_fh = new File(target_file);
                        if (!target_fh.exists()) {
                            String notFoundFileName = target_fh.getName();
                            if (!notFoundFileList.contains(notFoundFileName)) {
                                notFoundFileList.add(notFoundFileName);
                                System.err.println("File " + notFoundFileName +" not found");
                            }
                            continue;
                        }
                        
                        String target_text;
                        
                        try {
                            target_text = readFile(target_file, Charset.defaultCharset());
                            String results = target_text;
                            
                            if (cmd.hasOption("b")) {
                                File output_file = new File(target_file + ".bck");    // produce a backup file.
                                if (!output_file.exists()) {
                                    output_file.createNewFile(); // if file already exists will do nothing ????
                                    
                                    FileWriter fileWriter = new FileWriter(output_file, false);
                                    fileWriter.write(results);
                                    fileWriter.close();
                                } else {
                                    File tmp = new File(target_file);
                                    System.err.println("Not performing replace for " + tmp.getName() + ": Backup file already exists");
                                }
                            }
                            
                            
                            if (cmd.getOptions().length == 0 ) {
                                results = NormalR(results, replace_from, replace_to);
                            }
                            
                            if (cmd.getOptions().length == 1 && cmd.hasOption("b")) {
                                results = NormalR(results, replace_from, replace_to);
                            }
                            
                            if (cmd.hasOption("i")) {
                                if (cmd.getOptions().length == 1) {
                                    results = NonCase(results, replace_from, replace_to);
                                } else if (cmd.getOptions().length > 1) {
                                    if (cmd.hasOption("f")) {
                                        results = First_i(results, replace_from, replace_to);
                                        System.out.println(results);
                                    }
                                    if (cmd.hasOption("l")) {
                                        results = Last_i(results, replace_from, replace_to);
                                    }
                                }
                            } else {
                                if (cmd.hasOption("f")) {
                                    results = First(results, replace_from, replace_to);
                                }
                                if (cmd.hasOption("l")) {
                                    results = Last(results, replace_from, replace_to);
                                }
                            }
                            
                            
                            File output_file = new File(target_file);
                            // true to append, false to overwrite.
                            FileWriter fileWriter = new FileWriter(output_file, false);
                            fileWriter.write(results);
                            fileWriter.close();
                            
                        } catch (IOException e) {
                            // do nothing
                        }
                    }
                    
                } else if (inputArgs[start].compareTo(args[j]) != 0 &&
                           start == j && inputArgs[start]== args[sep_position+1] && sep_position-j>1){
                    
                    j=j+1;
                    
                    String replace_from = args[j];
                    String replace_to = args[j + 1];
                    
                    for (int afterSep = 1; sep_position + afterSep < args.length; afterSep++) {
                        String target_file = args[sep_position + afterSep];
                        
                        File target_fh = new File(target_file);
                        if (!target_fh.exists()) {
                            System.err.println("File "+ target_fh.getName() +" not found");
                            continue;
                        }
                        
                        String target_text;
                        
                        try {
                            target_text = readFile(target_file, Charset.defaultCharset());
                            String results = target_text;
                            
                            if (cmd.hasOption("b")) {
                                File output_file = new File(target_file + ".bck");    // produce a backup file.
                                if (!output_file.exists()) {
                                    output_file.createNewFile(); // if file already exists will do nothing ????
                                    
                                    FileWriter fileWriter = new FileWriter(output_file, false);
                                    fileWriter.write(results);
                                    fileWriter.close();
                                }
                                else {
                                    File tmp = new File(target_file);
                                    System.err.println("Not performing replace for "+tmp.getName()+": Backup file already exists");
                                }
                            }
                            
                            if (cmd.getOptions().length == 0 ) {
                                results = NormalR(results, replace_from, replace_to);
                            }
                            
                            if (cmd.getOptions().length == 1 && cmd.hasOption("b")) {
                                results = NormalR(results, replace_from, replace_to);
                            }
                            
                            if (cmd.hasOption("i")) {
                                if (cmd.getOptions().length == 1) {
                                    results = NonCase(results, replace_from, replace_to);
                                } else if (cmd.getOptions().length > 1) {
                                    if (cmd.hasOption("f")) {
                                        results = First_i(results, replace_from, replace_to);
                                        System.out.println(results);
                                    }
                                    if (cmd.hasOption("l")) {
                                        results = Last_i(results, replace_from, replace_to);
                                    }
                                }
                            } else {
                                if (cmd.hasOption("f")) {
                                    results = First(results, replace_from, replace_to);
                                }
                                if (cmd.hasOption("l")) {
                                    results = Last(results, replace_from, replace_to);
                                }
                            }
                            
                            
                            File output_file = new File(target_file);
                            // true to append, false to overwrite.
                            FileWriter fileWriter = new FileWriter(output_file, false);
                            fileWriter.write(results);
                            fileWriter.close();
                            
                        } catch (IOException e) {
                            // do nothing
                        }
                    }
                    
                }
                
                j = j + 1;
            }
        }catch(Exception e2){
            usage();
            return;
        }
    }
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////
    public static String readFile(String path, Charset encoding) throws IOException
    {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        }catch (IOException e) {
            // handle case 32
            Path p = Paths.get(path);
            String file = p.getFileName().toString();
            throw e;
        }
    }
    
    // replacement commands:
    private static String Last(String target_text, String replace_text, String replace_result) {
        return target_text.replaceFirst("(?s)(.*)" + replace_text, "$1" + replace_result);
    }
    private static String First(String target_text, String replace_text, String replace_result) {
        return target_text.replaceFirst(replace_text, replace_result);
    }
    private static String Last_i(String target_text, String replace_text, String replace_result) {
        return target_text.replaceFirst("(?i)"+"(?s)(.*)" + replace_text, "$1" + replace_result);
    }
    private static String First_i(String target_text, String replace_text, String replace_result) {
        return target_text.replaceFirst("(?i)"+replace_text, replace_result);
    }
    private static String NonCase(String target_text, String replace_text, String replace_result) {
        return target_text.replaceAll("(?i)"+replace_text, replace_result);
    }
    private static String NormalR(String target_text, String replace_text, String replace_result) {
        return target_text.replaceAll(replace_text, replace_result);
    }
    
    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }
    
}
