package de.queaker.guesswho;

/*
 * 
 * A great helper from "Whome"
 * https://stackoverflow.com/users/185565/whome
 * posted Sep 23, 2013 at 9:08 on
 * https://stackoverflow.com/questions/882772/capturing-stdout-when-calling-runtime-exec/18955510#18955510
 * 
 * As noted in the Stack Exchange Terms of Service and in the footer of every page, 
 * all publicly accessible user contributions are licensed under 
 * Creative Commons Attribution-ShareAlike license as follows:
 * 
 * Content contributed on or after 2018-05-02 (UTC) is distributed under the terms of CC BY-SA 4.0.
 * 
 */

import java.io.*;

/**
 * Execute external process and optionally read output buffer.
 */
public class ShellExec {
    private int exitCode;
    private boolean readOutput, readError;
    private StreamGobbler errorGobbler, outputGobbler;

    public ShellExec() { 
        this(false, false);
    }

    public ShellExec(boolean readOutput, boolean readError) {
        this.readOutput = readOutput;
        this.readError = readError;
    }

    /**
     * Execute a command.
     * @param command   command ("c:/some/folder/script.bat" or "some/folder/script.sh")
     * @param workdir   working directory or NULL to use command folder
     * @param wait  wait for process to end
     * @param args  0..n command line arguments
     * @return  process exit code
     */
    public int execute(String command, String workdir, boolean wait, String...args) throws IOException {
        String[] cmdArr;
        if (args != null && args.length > 0) {
            cmdArr = new String[1+args.length];
            cmdArr[0] = command;
            System.arraycopy(args, 0, cmdArr, 1, args.length);
        } else {
            cmdArr = new String[] { command };
        }

        ProcessBuilder pb =  new ProcessBuilder(cmdArr);
        File workingDir = (workdir==null ? new File(command).getParentFile() : new File(workdir) );
        pb.directory(workingDir);

        Process process = pb.start();

        // Consume streams, older jvm's had a memory leak if streams were not read,
        // some other jvm+OS combinations may block unless streams are consumed.
        errorGobbler  = new StreamGobbler(process.getErrorStream(), readError);
        outputGobbler = new StreamGobbler(process.getInputStream(), readOutput);
        errorGobbler.start();
        outputGobbler.start();

        exitCode = 0;
        if (wait) {
            try { 
                process.waitFor();
                exitCode = process.exitValue();                 
            } catch (InterruptedException ex) { }
        }
        return exitCode;
    }   

    public int getExitCode() {
        return exitCode;
    }

    public boolean isOutputCompleted() {
        return (outputGobbler != null ? outputGobbler.isCompleted() : false);
    }

    public boolean isErrorCompleted() {
        return (errorGobbler != null ? errorGobbler.isCompleted() : false);
    }

    public String getOutput() {
        return (outputGobbler != null ? outputGobbler.getOutput() : null);        
    }

    public String getError() {
        return (errorGobbler != null ? errorGobbler.getOutput() : null);        
    }

//********************************************
//********************************************    

    /**
     * StreamGobbler reads inputstream to "gobble" it.
     * This is used by Executor class when running 
     * a commandline applications. Gobblers must read/purge
     * INSTR and ERRSTR process streams.
     * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4
     */
    private class StreamGobbler extends Thread {
        private InputStream is;
        private StringBuilder output;
        private volatile boolean completed; // mark volatile to guarantee a thread safety

        public StreamGobbler(InputStream is, boolean readStream) {
            this.is = is;
            this.output = (readStream ? new StringBuilder(256) : null);
        }

        public void run() {
            completed = false;
            try {
                String NL = System.getProperty("line.separator", "\r\n");

                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ( (line = br.readLine()) != null) {
                    if (output != null)
                        output.append(line + NL); 
                }
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
            completed = true;
        }

        /**
         * Get inputstream buffer or null if stream
         * was not consumed.
         * @return
         */
        public String getOutput() {
            return (output != null ? output.toString() : null);
        }

        /**
         * Is input stream completed.
         * @return
         */
        public boolean isCompleted() {
            return completed;
        }

    }

}
