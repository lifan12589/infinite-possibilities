package com.infinitePossibilities.config;


import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.infinitePossibilities.database.Ac_newConfig;
import com.infinitePossibilities.database.Ac_oldConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FkFbShardingAlgorithm {

    @Autowired
    private Ac_newConfig ac_newConfig;

    @Autowired
    private Ac_oldConfig ac_OldConfig;

    @Autowired
    private FbShardingAlgorithm fbShardingAlgorithm;

    @Autowired
    private FkShardingAlgorithm fkShardingAlgorithm;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {

        //分库设置
        Map<String,DataSource>  dataSourcemap = new HashMap<>(2);

        //添加两个数据库 Ac_new 和 Ac_old
        dataSourcemap.put(ac_newConfig.getDatabaseName(),ac_newConfig.createDataSource());
        dataSourcemap.put(ac_OldConfig.getDatabaseName(),ac_OldConfig.createDataSource());

        DataSourceRule dataSourceRule = new DataSourceRule(dataSourcemap,ac_newConfig.getDatabaseName());

        //分表设置，大致思想就是将查询虚拟表Save根据一定规则映射到真实表中去
        TableRule orderTableRule = TableRule.builder("save")
                .actualTables(Arrays.asList("one", "two"))
                .dataSourceRule(dataSourceRule)
                .build();


        //分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("id", fkShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy("type",fbShardingAlgorithm)).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        return dataSource;

    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }


}
