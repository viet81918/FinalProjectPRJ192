/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddList implements Serializable {
    private Map<Admin, List<Novel>> adminNovelMap; // Map<Admin, List<Novel>>

    public AddList() {
        this.adminNovelMap = new HashMap<>();
    }

    public void addNovel(Admin admin, Novel novel) {
        if (!adminNovelMap.containsKey(admin)) {
            adminNovelMap.put(admin, new ArrayList<>());
        }
        adminNovelMap.get(admin).add(novel);
    }

    public Map<Admin, List<Novel>> getAdminNovelMap() {
        return adminNovelMap;
    }

    public AddList(Map<Admin, List<Novel>> adminNovelMap) {
        this.adminNovelMap = adminNovelMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddList{");
        sb.append("adminNovelMap={");
        for (Map.Entry<Admin, List<Novel>> entry : adminNovelMap.entrySet()) {
            Admin admin = entry.getKey();
            List<Novel> novels = entry.getValue();
            sb.append(admin.getAdminId()).append("=[");
            for (Novel novel : novels) {
                sb.append(novel.getNovelId()).append(", ");
            }
            if (!novels.isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove the last comma and space
            }
            sb.append("], ");
        }
        if (!adminNovelMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("}}");
        return sb.toString();
    }
}