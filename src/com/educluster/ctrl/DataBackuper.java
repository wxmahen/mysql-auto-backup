package com.educluster.ctrl;

import com.educluster.props.Properties;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahen Samaranayake
 */
public class DataBackuper {

    public void startBackup() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        backup();
                        Thread.sleep(Properties.PERIOD * 60 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(DataBackuper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void backup() {
        try {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String file_name = Properties.BACKUP_DIR + "\\" + df.format(date) + ".sql\"";
            String cmd = "\"" + Properties.BIN_PATH + "\\mysqldump.exe\" -h" + Properties.HOSTNAME + " -P" + Properties.PORT
                    + " -u" + Properties.USER + " -p" + Properties.PASSWORD + " " + Properties.DATABASE + " -r " + file_name;
            Process p = Runtime.getRuntime().exec(cmd);
            int completed = p.waitFor();
            System.out.println(completed);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataBackuper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataBackuper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
