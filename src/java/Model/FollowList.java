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

public class FollowList implements Serializable {
    private Map<Customer, List<Novel>> customerNovelMap; // Map<Customer, List<Novel>>

    public FollowList() {
        this.customerNovelMap = new HashMap<>();
    }

    public void addNovel(Customer customer, Novel novel) {
        if (!customerNovelMap.containsKey(customer)) {
            customerNovelMap.put(customer, new ArrayList<>());
        }
        customerNovelMap.get(customer).add(novel);
    }

    public Map<Customer, List<Novel>> getCustomerNovelMap() {
        return customerNovelMap;
    }

    public FollowList( Map<Customer, List<Novel>> customerNovelMap) {
        
        this.customerNovelMap = customerNovelMap;
    }

   

   
  @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("FollowList{");
    sb.append("customerNovelMap={");
    for (Map.Entry<Customer, List<Novel>> entry : customerNovelMap.entrySet()) {
        Customer customer = entry.getKey();
        List<Novel> novels = entry.getValue();
        sb.append(customer.getCustomerId()).append("=[");
        for (Novel novel : novels) {
            sb.append(novel.getNovelId()).append(", ");
        }
        if (!novels.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("], ");
    }
    if (!customerNovelMap.isEmpty()) {
        sb.setLength(sb.length() - 2); // Remove the last comma and space
    }
    sb.append("}}");
    return sb.toString();
}

  public void removeNovel(Customer customer, Novel novel) {
        List<Novel> customerNovels = customerNovelMap.get(customer);
        if (customerNovels != null) {
            customerNovels.remove(novel);
        }
    }

}