package framework.commonFunctions;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.map.MultiKeyMap;


public class mapHelper {
    public static String mapProperties(String partner, String environment){
        MultiKeyMap multiKeyMap = MultiKeyMap.multiKeyMap(new LinkedMap<>());
        multiKeyMap.put("ace","stg","staging/ace");
        multiKeyMap.put("ace","staging","staging/ace");
        multiKeyMap.put("attachment","stg","staging/attachment");
        multiKeyMap.put("attachment","staging","staging/attachment");
        multiKeyMap.put("briefing","stg","staging/briefing");
        multiKeyMap.put("briefing","staging","staging/briefing");
        multiKeyMap.put("dnd","stg","staging/dnd");
        multiKeyMap.put("dnd","staging","staging/dnd");
        multiKeyMap.put("ecchi","stg","staging/ecchi");
        multiKeyMap.put("ecchi","staging","staging/ecchi");
        multiKeyMap.put("haku","stg","staging/haku");
        multiKeyMap.put("haku","staging","staging/haku");
        multiKeyMap.put("igc","stg","staging/igc");
        multiKeyMap.put("igc","staging","staging/igc");
        multiKeyMap.put("mago","stg","staging/mago");
        multiKeyMap.put("mago","staging","staging/mago");
        multiKeyMap.put("makuake","stg","staging/makuake");
        multiKeyMap.put("makuake","staging","staging/makuake");
        multiKeyMap.put("metaphore","stg","staging/metaphore");
        multiKeyMap.put("metaphore","staging","staging/metaphore");
        multiKeyMap.put("maruzeki","stg","staging/maruzeki");
        multiKeyMap.put("maruzeki","staging","staging/maruzeki");
        multiKeyMap.put("swans","stg","staging/swans");
        multiKeyMap.put("swans","staging","staging/swans");
        multiKeyMap.put("talex","stg","staging/talex");
        multiKeyMap.put("talex","staging","staging/talex");
        multiKeyMap.put("tanuki","stg","staging/tanuki");
        multiKeyMap.put("tanuki","staging","staging/tanuki");
        multiKeyMap.put("tatras","stg","staging/tatras");
        multiKeyMap.put("tatras","staging","staging/tatras");
        multiKeyMap.put("toot","stg","staging/toot");
        multiKeyMap.put("toot","staging","staging/toot");
        multiKeyMap.put("xlarge","stg","staging/xlarge");
        multiKeyMap.put("xlarge","staging","staging/xlarge");
        multiKeyMap.put("xgirl","stg","staging/xgirl");
        multiKeyMap.put("xgirl","staging","staging/xgirl");
        multiKeyMap.put("kokuyo","stg","staging/kokuyo");
        multiKeyMap.put("kokuyo","staging","staging/kokuyo");
        multiKeyMap.put("acate","prod","production/acate");
        multiKeyMap.put("acate","production","production/acate");
        multiKeyMap.put("ace","prod","production/ace");
        multiKeyMap.put("ace","production","production/ace");
        multiKeyMap.put("attachment","prod","production/attachment");
        multiKeyMap.put("attachment","production","production/attachment");
        multiKeyMap.put("briefing","prod","production/briefing");
        multiKeyMap.put("briefing","production","production/briefing");
        multiKeyMap.put("dnd","prod","production/dnd");
        multiKeyMap.put("dnd","production","production/dnd");
        multiKeyMap.put("ecchi","prod","production/ecchi");
        multiKeyMap.put("ecchi","production","production/ecchi");
        multiKeyMap.put("haku","prod","production/haku");
        multiKeyMap.put("haku","production","production/haku");
        multiKeyMap.put("igc","prod","production/igc");
        multiKeyMap.put("igc","production","production/igc");
        multiKeyMap.put("mago","prod","production/mago");
        multiKeyMap.put("mago","production","production/mago");
        multiKeyMap.put("makuake","prod","production/makuake");
        multiKeyMap.put("makuake","production","production/makuake");
        multiKeyMap.put("metaphore","prod","production/metaphore");
        multiKeyMap.put("metaphore","production","production/metaphore");
        multiKeyMap.put("maruzeki","prod","production/maruzeki");
        multiKeyMap.put("maruzeki","production","production/maruzeki");
        multiKeyMap.put("swans","prod","production/swans");
        multiKeyMap.put("swans","production","production/swans");
        multiKeyMap.put("talex","prod","production/talex");
        multiKeyMap.put("talex","production","production/talex");
        multiKeyMap.put("tanuki","prod","production/tanuki");
        multiKeyMap.put("tanuki","production","production/tanuki");
        multiKeyMap.put("tatras","prod","production/tatras");
        multiKeyMap.put("tatras","production","production/tatras");
        multiKeyMap.put("toot","prod","production/toot");
        multiKeyMap.put("toot","production","production/toot");
        multiKeyMap.put("xlarge","prod","production/xlarge");
        multiKeyMap.put("xlarge","production","production/xlarge");
        multiKeyMap.put("xgirl","prod","production/xgirl");
        multiKeyMap.put("xgirl","production","production/xgirl");
        multiKeyMap.put("kokuyo","prod","production/kokuyo");
        multiKeyMap.put("kokuyo","production","production/kokuyo");
        multiKeyMap.put("momotaro","prod","production/momotaro");
        multiKeyMap.put("momotaro","production","production/momotaro");
        multiKeyMap.put("samurai","prod","production/samurai");
        multiKeyMap.put("samurai","production","production/samurai");
        multiKeyMap.put("japanblue","prod","production/japanblue");
        multiKeyMap.put("japanblue","production","production/japanblue");
        multiKeyMap.put("knifan","prod","production/knifan");
        multiKeyMap.put("knifan","production","production/knifan");
        return multiKeyMap.get(partner,environment).toString();
    }
}
