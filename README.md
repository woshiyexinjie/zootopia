# zootopia 疯狂动物城
来看看各个动物都干了什么

## monkey ##  
&ensp;monkey这种动物比较好动，也比较有灵性  
&ensp;&ensp;我在这里简单地先用selenium爬取了豆瓣的上映电影 
&ensp;&ensp;使用的技术  
&ensp;&ensp;1.selenium  
&ensp;&ensp;2.slf4j+logback  
&ensp;&ensp;3.spring schedule  

## mouse ## 
比较灵活，会打洞那么用来查数据  
&ensp;&ensp;1.mybatis查数据
当然这里我也考虑过其他的技术选型，但是感觉还是mybatis更加适合

## peacock ##  
这个应该用来写页面呀 但是不知该怎么写的好  

## swordfish ##
因为其他服务可能用到一个全局递增id，而且服务是可以分布式，而且互不影响   
&ensp;&ensp;1.XinSnowWorkIdGenerator 参考Twitter_Snowflake算法   
&ensp;&ensp;0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000   
&ensp;&ensp;2.XinImproveSnowGenerator 改进Twitter_Snowflake算法 感觉更实用 生成的订单是固定长度的，加大了时间戳序列的长度   
&ensp;&ensp;01 - 0000000000 0000000000 0000000000 0000000000 000 - 0000000 - 000000000000    
