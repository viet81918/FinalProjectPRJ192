/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class ThemeList {
      private Map<Theme, List<Novel>> themeNovelMap;

    public ThemeList() {
        this.themeNovelMap = new HashMap<>();
    }

    public void addNovel(Theme theme, Novel novel) {
        if (!themeNovelMap.containsKey(theme)) {
            themeNovelMap.put(theme, new ArrayList<>());
        }
        themeNovelMap.get(theme).add(novel);
    }

    public Map<Theme, List<Novel>> getThemeNovelMap() {
        return themeNovelMap;
    }

    public ThemeList(Map<Theme, List<Novel>> themeNovelMap) {
        this.themeNovelMap = themeNovelMap;
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FormatList{");
        sb.append("formatNovelMap={");
        for (Map.Entry<Theme, List<Novel>> entry : themeNovelMap.entrySet()) {
            Theme key = entry.getKey();
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
        if (!themeNovelMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }
}
