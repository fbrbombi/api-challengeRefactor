package utils;

import trelloObjects.TrelloObject;

public class SearcherAttributes {

    public static String findIdByName(TrelloObject[] trelloObject, String name) {
        for (TrelloObject trelloObject1 : trelloObject) {
            if (trelloObject1.getName().equals(name)) {
                return trelloObject1.getId();
            }
        }
        return "Not found";
    }

    public static String findByName(TrelloObject[] trelloObject, String name) {
        for (TrelloObject trelloObject1 : trelloObject) {
            if (trelloObject1.getName().equals(name)) {
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

    public static String getRandomMemberIdOfTheBoard(TrelloObject[] trelloObjects) {
        int position = RandomGenerator.generateRandomNumber(trelloObjects.length - 1, 0);
        return trelloObjects[position].getId();
    }


    public static boolean isThisIdPartOfThisList(String id, TrelloObject[] trelloObjects) {
        for (TrelloObject trelloObject : trelloObjects) {
            if (trelloObject.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
