package com.infinitePossibilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);

//        initFlowQpsRule();
    }

//    private static void initFlowQpsRule(){
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule(SentinelController.RESOURCE_NAME);
//        rule.setCount(2);
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule.setLimitApp("default");
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }

}
