package utils;

import controllers.Objects.TrelloObject;

public class SeacherAttributes {

    public static String findIdByName(TrelloObject[] trelloObject, String name) {
        int i;
        for (i = 0; i < (trelloObject.length); i++) {
            if (trelloObject[i].getName().equals(name)) {
                return trelloObject[i].getId();
            }
        }
        return "Not found";
    }
}
