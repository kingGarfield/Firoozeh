package Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class Utils {

    public static final String LOGIN_URL1 = "http://s-a-hejazi.ir/signin?username=";
    public static final String LOGIN_URL2 = "&password=";
    public static final String LOGIN_URL3 = "&api_key=123isagoodpassword";
    public static final String STUDENT_AVG_RECEIVE_PASS_URL = "http://s-a-hejazi.ir/markSummary?token=";
    public static final String ACCUNT_SUMMARY_URL = "http://s-a-hejazi.ir/account_information?token=";
    public static final String ANY_TERM_INFORMATION ="http://s-a-hejazi.ir/studentCourses?token=";


    public static JSONObject getObject (JSONObject object, String tag) throws JSONException {
        return object.getJSONObject(tag);
    }
    public static String getString (JSONObject object, String tag) throws JSONException {
        return object.getString(tag);
    }
}
