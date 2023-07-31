package com.costExplorer;

import java.util.List;

public interface CostService {

    List<String> getMonthlyCosts(int customerId);
}
