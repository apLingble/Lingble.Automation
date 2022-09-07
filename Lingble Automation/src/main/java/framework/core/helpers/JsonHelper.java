package framework.core.helpers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JsonHelper {

    protected static final String JSON_PATH_PREFIX = "$.";
    private static final String JSON_ARRAY_INDEX_PATTERN = "\\((.*?)\\)";

    protected static String getJsonPath(String jsonPath, String... prefix) {
        String targetJsonPath;
        if (prefix.length == 1) {
            targetJsonPath = prefix[0] + jsonPath;
        } else {
            targetJsonPath = JSON_PATH_PREFIX + jsonPath;
        }
        if (targetJsonPath.endsWith(".")) {
            targetJsonPath = targetJsonPath.substring(0, targetJsonPath.length() - 1);
        }
        return targetJsonPath;
    }

    public static boolean containsKey(JSONObject jsonObj, String jsonPath) {
        return JSONPath.contains(jsonObj, jsonPath);
    }

    public static String getValueFromJsonObject(JSONObject jsonObj, String jsonPath,
                                                String... prefix) {
        Object resultObject = JSONPath.eval(jsonObj, getJsonPath(jsonPath, prefix));
        if (null == resultObject) {
            return null;
        }
        return resultObject.toString();
    }

    public static Object getObjectFromJsonObject(JSONObject jsonObj, String jsonPath,
                                                 String... prefix) {
        Object resultObject = JSONPath.eval(jsonObj, getJsonPath(jsonPath, prefix));
        if (null == resultObject) {
            return null;
        }
        return resultObject;
    }


    public static String getValueFromJsonString(String jsonStr, String jsonPath, String... prefix) {
        return getValueFromJsonObject(JSON.parseObject(jsonStr), jsonPath, prefix);
    }


    public static String getPrettyJson(String origin) {
        try {
            return JSON.toJSONString(JSON.parseObject(origin), true);
        } catch (Exception e) {
            return origin;
        }
    }

    public static JSONObject setStringByJsonPath(JSONObject obj, String jsonPath, Object value) {
        if (!jsonPath.contains(".")) {
            if (null == value) {
                obj.remove(jsonPath);
                return obj;
            }
            else {
                String singleArrayIndex = getFirstValueFromPattern(jsonPath, JSON_ARRAY_INDEX_PATTERN, 1);
                if (null != singleArrayIndex && StringHelper.isNumeric(singleArrayIndex)) {
                    String curPath = jsonPath.split("\\(")[0];
                    JSONArray array = obj.getJSONArray(curPath);
                    if (null == array) {
                        obj.put(curPath, new JSONArray());
                        array = obj.getJSONArray(curPath);
                    }
                    array.set(Integer.parseInt(singleArrayIndex), value);
                } else {
                    obj.put(jsonPath, value);
                }
                return obj;
            }
        }
        String[] pathList = jsonPath.split("\\.", 2);
        String arrayIndex = getFirstValueFromPattern(pathList[0], JSON_ARRAY_INDEX_PATTERN, 1);

        // parse json path, specify json array
        String currentPath = null;
        if (null != arrayIndex && StringHelper.isNumeric(arrayIndex)) {
            currentPath = pathList[0].split("\\(")[0];
        } else {
            currentPath = pathList[0];
        }
        // add jsonObj or jsonArray if key not exist
        if (!obj.containsKey(currentPath)) {
            if (null == arrayIndex) {
                obj.put(currentPath, new JSONObject());
            } else {
                obj.put(currentPath, new JSONArray());
            }
        }

        setStringByJsonPath(
                null == arrayIndex? obj.getJSONObject(currentPath): obj.getJSONArray(currentPath).getJSONObject(Integer.parseInt(arrayIndex)),
                pathList[1], value
        );
        return obj;
    }

    private static String getFirstValueFromPattern(String oriString, String pattern, int... patternCount) {
        Matcher matcher = Pattern.compile(pattern).matcher(oriString);
        if (!matcher.find()) {
            return null;
        }
        return patternCount.length != 0 ? matcher.group(patternCount[0]): matcher.group();
    }
}