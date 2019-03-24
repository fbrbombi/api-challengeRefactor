package utils;

import controllers.Objects.TrelloObject;

public class SearcherAttributes {

    public static String findIdByName(TrelloObject[] trelloObject, String name) {
        int i;
        for (i = 0; i < (trelloObject.length); i++) {
            if (trelloObject[i].getName().equals(name)) {
                return trelloObject[i].getId();
            }
        }
        return "Not found";
    }

    public static String findByName(TrelloObject[] trelloObject, String name) {
        int i;
        for (i = 0; i < (trelloObject.length); i++) {
            if (trelloObject[i].getName().equals(name)) {
                return "Found";
            }
        }
        return "Not found";
    }

    public static String findPositionOfTrelloElement(TrelloObject[] trelloObject, String name) {
        for (TrelloObject trelloObject1 : trelloObject) {
            if (trelloObject1.getName().equals(name)) {
                return trelloObject1.getPos();
            }
        }
        return "Not found";
    }

    public static boolean isThereAListWithName(TrelloObject[] trelloObject, String name) {
        for (TrelloObject trelloObject1 : trelloObject) {
            if (trelloObject1.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String getRandomMemberOfTheBoard(TrelloObject[] trelloObjects) {
        return "";
    }

}
