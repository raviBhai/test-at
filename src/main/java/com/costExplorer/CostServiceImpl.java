package com.costExplorer;

import java.util.List;

public class CostServiceImpl implements CostService {

    DataProvider dataProvider;

    public CostServiceImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public List<String> getMonthlyCosts(int customerId) {
        return dataProvider.getCustomerCosts(customerId);
    }
}
