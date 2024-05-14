/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelFormat {
    private Map<Novel, List<Format>> novelFormatMap; // Map<Novel, List<Format>>

    public NovelFormat() {
        this.novelFormatMap = new HashMap<>();
    }

    public void addFormat(Novel novel, Format format) {
        if (!novelFormatMap.containsKey(novel)) {
            novelFormatMap.put(novel, new ArrayList<>());
        }
        novelFormatMap.get(novel).add(format);
    }

    public Map<Novel, List<Format>> getNovelFormatMap() {
        return novelFormatMap;
    }
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("NovelFormat{\n");
    for (Map.Entry<Novel, List<Format>> entry : novelFormatMap.entrySet()) {
        Novel novel = entry.getKey();
        List<Format> formats = entry.getValue();
        sb.append("\t").append("Novel ID: ").append(novel.getNovelId()).append("\n");
        sb.append("\t").append("Formats: ");
        if (formats.isEmpty()) {
            sb.append("No formats\n");
        } else {
            sb.append(formats).append("\n");
        }
    }
    sb.append("}");
    return sb.toString();
}
}
