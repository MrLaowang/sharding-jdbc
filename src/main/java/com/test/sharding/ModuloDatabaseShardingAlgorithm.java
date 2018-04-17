package com.test.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

/**
 * /**
 * 表示t_order表按user_id进行分成ds_0,ds_1共俩库，每个库中又按order_id分成t_order_0,t_order_1二张表
 *分库、分表是按常见的取模算法处理的
 */
public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String targetName : availableTargetNames) {
                if (targetName.endsWith(value % 2 + "")) {
                    result.add(targetName);
                }
            }
        }
        return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
	}


}
