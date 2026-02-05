public class parser {

    public static String parseDescription(String[] parts){
        //by the case return the required text the task wants
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < parts.length; i++){
            if(parts[i].equals("/by") || parts[i].equals("/from") || parts[i].equals("/to")){
                break;
            }
            sb.append(parts[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static String startDate(String[] parts){
        int fromIndex = -1;
        int toIndex = -1;

        for(int i = 0; i < parts.length; i++){
            if(parts[i].equals("/from")){
                fromIndex = i;
            }
            else if(parts[i].equals("/to")){
                toIndex = i;
            }
        }

        if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
            StringBuilder startDate = new StringBuilder();
            for (int i = fromIndex + 1; i < toIndex; i++) {
                startDate.append(parts[i]).append(" ");
            }
            return startDate.toString().trim();
        } else {
            return "Invalid input format";
        }
    }

    public static String endDate(String[] parts){
        //locate either the /to or the /by position

        int fromIndex = -1;

        for(int i = 0; i < parts.length; i++){
            if(parts[i].equals("/by") || parts[i].equals("/to")){
                fromIndex = i;
                break;
            }
        }

        if(fromIndex != -1){
            StringBuilder endDate = new StringBuilder();
            for(int i = fromIndex + 1; i < parts.length; i++){
                endDate.append(parts[i]).append(" ");
            }
            return endDate.toString().trim();
        }else{
            return "Invalid input format";
        }
    }
}
