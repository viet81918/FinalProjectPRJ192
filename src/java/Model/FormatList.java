/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class FormatList implements Serializable {
    private Map<Format, List<Novel>> formatNovelMap;

    public FormatList() {
        this.formatNovelMap = new HashMap<>();
    }

    public void addNovel(Format format, Novel novel) {
        if (!formatNovelMap.containsKey(format)) {
            formatNovelMap.put(format, new ArrayList<>());
        }
        formatNovelMap.get(format).add(novel);
    }

    public Map<Format, List<Novel>> getFormatNovelMap() {
        return formatNovelMap;
    }

    public FormatList(Map<Format, List<Novel>> formatNovelMap) {
        this.formatNovelMap = formatNovelMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FormatList{");
        sb.append("formatNovelMap={");
        for (Map.Entry<Format, List<Novel>> entry : formatNovelMap.entrySet()) {
            Format key = entry.getKey();
            List<Novel> novels = entry.getValue();
            sb.append(key.getTypeId()).append("=[");
            for (Novel novel : novels) {
                sb.append(novel.getNovelId()).append(", ");
            }
            if (!novels.isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove the last comma and space
            }
            sb.append("], ");
        }
        if (!formatNovelMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }
}