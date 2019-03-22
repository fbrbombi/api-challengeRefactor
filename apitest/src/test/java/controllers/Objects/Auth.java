package controllers.Objects;

import net.thucydides.core.steps.ScenarioSteps;

import java.util.HashMap;
import java.util.Map;

public class Auth extends ScenarioSteps {

    private static final String KEY = "d2984f8961d4891ce52807463a8d8d31";
   private static final String TOKEN = "eada265ac1a94a06dd3391307f856f5e39694b53e3100fc1397532740fcc98ec";
    //private static  String KEY ;
    //private static  String TOKEN;
    private static final String CONTENT_TYPE = "application/json";
    private static Auth instance;
    private static final Map<String, String> params = new HashMap<>();

    public static synchronized Auth getInstance(){
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    private Auth(){
       // GetterConfig read = new GetterConfig("config.txt");
       // KEY=read.getParts(0);
       // TOKEN=read.getParts(1);
        params.put("key", KEY);
        params.put("token", TOKEN);
    }

    public Map<String, String> getAuthParams() {
        return params;
    }

    public String getKey() {
        return KEY;
    }

    public String getToken() {
        return TOKEN;
    }


}
