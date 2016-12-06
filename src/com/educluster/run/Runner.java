package com.educluster.run;

import com.educluster.ctrl.DataBackuper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahen Samaranayake
 */
public class Runner {

    public static void main(String[] args) {
        setProperties();
        startBackuping();
    }

    private static void setProperties() {
        try {
            File file = new File("settings.ec");
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            String hostname = properties.getProperty("hostname");
            String port = properties.getProperty("port");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String database = properties.getProperty("database");
            String bin = properties.getProperty("bin");
            com.educluster.props.Properties.HOSTNAME = hostname;
            com.educluster.props.Properties.PORT = port;
            com.educluster.props.Properties.USER = username;
            com.educluster.props.Properties.PASSWORD = password;
            com.educluster.props.Properties.DATABASE = database;
            com.educluster.props.Properties.BIN_PATH = bin;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void startBackuping() {
        DataBackuper backuper = new DataBackuper();
        backuper.startBackup();
    }
}
