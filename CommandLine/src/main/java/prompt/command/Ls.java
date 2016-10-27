package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import file_system_adapter.FSAdapter;

import java.util.Objects;

public class Ls extends Command {

    public Ls(CommandPrompt prompt){
        super(prompt, "ls");
    }

    public String doCommand(PathContainer currentDir, String input) {
        String output = "";
        String[] fsoNames = selectLSType(currentDir, input);

        for (String name : fsoNames) {
            output += name + "\n";
        }
        return output;
    }

    private String[] selectLSType(PathContainer currentDir, String input){
        FSAdapter adapter = super.getAdapter();
        String currDir = currentDir.getPath();
        String fullPath;

        if (input != null && input.length() > 0) {
            if(input.startsWith("-")){
                fullPath = currDir;
                if(input.equals("-dirs") || input.equals("-Dirs")){
                    return adapter.lsDir(fullPath);
                }
                if(input.equals("-files") || input.equals("-Files")){
                    return adapter.lsFile(fullPath);
                } else { //fall input börjar på - men inte är någon av rätt alternativ utförs vanlig ls i mapp man står i.
                    fullPath = PathContainer.getFullPath(currDir, input);
                    return adapter.ls(fullPath);
                }
            }
            if(!input.startsWith("-") && input.contains("-")){
                String path = input.split(" -")[0];
                String sort = input.split(" -")[1];
                fullPath = PathContainer.getFullPath(currDir, path);
                if(sort.equals("dirs") || sort.equals("Dirs")){
                    return adapter.lsDir(fullPath);
                }
                if(sort.equals("files") || sort.equals("Files")){
                    return adapter.lsFile(fullPath);
                } else { //fall input är String följt utav " -" + ett sorterings alternativ som inte följs görs ls på path'en man valde
                    fullPath = PathContainer.getFullPath(currDir, path);
                    return adapter.ls(fullPath);
                }
            }else{ //ls med sökväg
                fullPath = PathContainer.getFullPath(currDir, input);
                return adapter.ls(fullPath);
            }
        } else { //ls i currentDir
            fullPath = currDir;
            return adapter.ls(fullPath);
        }
    }



    public int hashCode() {
        return Objects.hash(getName());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        Ls other = (Ls) o;

        return Objects.equals(getName(), other.getName());
    }
}