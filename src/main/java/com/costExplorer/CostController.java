package com.costExplorer;

import java.util.List;

public class CostController {

    CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    public Response getMonthlyCosts(int customerId) {
        List<String> montlyCosts = costService.getMonthlyCosts(customerId);
        return new Response<List>(montlyCosts);
    }
}
