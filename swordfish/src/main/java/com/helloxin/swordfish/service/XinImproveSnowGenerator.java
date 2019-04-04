package com.helloxin.swordfish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//改进Twitter_Snowflake算法 因为不同公司规模不同，而且以中小企业为主
//所以也没有那么多机房和机器 这个也可以有一个配置表 取配置的Id
//我们可以先把41位的时间戳改大点，而且我们发号器期望长度是不变的 因为最大2的63次方-1，是个18位数 我看最小的18位是 1101111000 0010110110 1011001110 1001110110 0100000000 0000000000
//所以01 就能确定位数啦
//然后我们就去掉开始计算差值的 twepoch，

/**
 * 改过的SnowFlake的结构如下(每部分用-分开):
 * 01 - 0000000000 0000000000 0000000000 0000000000 000 - 0000000 - 000000000000 <br>
 * 1位标识，最高位是0<br>
 * 标识位后的1确定位数
 * 41位时间截(毫秒级)，注意，43位时间截不是存储当前时间的时间截 69*2*2 年
 * 7位的数据机器位，来确定是一台机器，128台一般也够用啦
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。
 */

/**
 * Created by nandiexin on 2019/4/3.
 */
@Service
public class XinImproveSnowGenerator implements XinImproveSnowGenerate,InitializingBean {

    final private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 机器id所占的位数
     */
    private final long bitNum = 62L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 7L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);


    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;


    /**
     * 时间截向左移19位(19)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits ;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    @Value("${snow.flake.worker.id}")
    private long workerId;


    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;




    @Override
    public void afterPropertiesSet() throws Exception {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }


    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    @Override
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        logger.debug("timestamp={},sequence={}",timestamp,Long.toBinaryString(timestamp));
        logger.debug("workerId={},sequence={}",workerId,Long.toBinaryString(workerId));
        logger.debug("sequence={},sequence={}",sequence,Long.toBinaryString(sequence));

        //这里考虑时间戳超过我们的43位的情况

        //移位并通过或运算拼到一起组成64位的ID
        return (timestamp << timestampLeftShift)
                | (workerId << workerIdShift)
                | sequence
                | 1L << bitNum ;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


}
