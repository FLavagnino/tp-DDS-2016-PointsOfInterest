package ar.edu.utn.dds.poi.utils;

public class DBBackupUtil 
{
	public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) 
	{	 
        String executeCmd = "\"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump\" -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) 
            {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
	
	public boolean restoreDB(String dbUserName, String dbPassword, String source) 
	{	 
        String[] executeCmd = new String[]{"\"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql\"", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};
 
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
}
