package com.costExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {

    Map<Integer, List<String>> customerCosts;

    public DataProvider() {
        customerCosts = new HashMap<>();
        List<String> costs = new ArrayList<>();
        costs.add("100");
        costs.add("200");
        customerCosts.put(1, costs);
    }

    public List<String> getCustomerCosts(int customerId) {
        return customerCosts.get(customerId);
    }

}
