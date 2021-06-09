package com.infinitePossibilities.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.infinitePossibilities.database.Ac_newConfig;
import com.infinitePossibilities.database.Ac_oldConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class FkShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {


    @Autowired
    private Ac_newConfig ac_newConfig;

    @Autowired
    private Ac_oldConfig ac_OldConfig;
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames,final ShardingValue<Long> shardingValue) {

        Long value = shardingValue.getValue();
        if (value <= 20L) {
            return ac_newConfig.getDatabaseName();
        } else {
            return ac_OldConfig.getDatabaseName();
        }
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames,final ShardingValue<Long> shardingValue) {

        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            if (value <= 20L) {
                result.add(ac_newConfig.getDatabaseName());
            } else {
                result.add(ac_OldConfig.getDatabaseName());
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames,
                                                final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            if (value <= 20L) {
                result.add(ac_newConfig.getDatabaseName());
            } else {
                result.add(ac_OldConfig.getDatabaseName());
            }
        }
        return result;
    }
}
