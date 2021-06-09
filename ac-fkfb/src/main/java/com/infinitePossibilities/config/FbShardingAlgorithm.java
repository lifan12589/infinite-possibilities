package com.infinitePossibilities.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class FbShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {


    @Override
    public String doEqualSharding(final Collection<String> tableNames,final ShardingValue<Long> shardingValue) {

        for(String table : tableNames){
            System.out.println(table+"---"+shardingValue);
            System.out.println(shardingValue.getValue()%2+"");
            if("0".endsWith(shardingValue.getValue()%2+"")){
                return "one";
            }else{
                return "two";
            }
        }
        throw new IllegalArgumentException("无分表参数 无法定位具体数据表");
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> tableNames,final ShardingValue<Long> shardingValue) {

        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(String.valueOf(value))) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> tableNames,final ShardingValue<Long> shardingValue) {

        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

}
