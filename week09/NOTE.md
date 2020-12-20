#不同路径 II
https://leetcode-cn.com/problems/unique-paths-ii/  
##定义状态：  
1.dp[i][j] 表示从起始位置到达位置（i，j）的路径总数；  
2.s[i][j] 表示坐标（i，j）是否是障碍，s[i][j]=0表示是障碍，为1则不是障碍。 
##状态转移方程
1.s[i][j]=0，dp[i][j]=0;  
2.s[i][j]=1，dp[i][j]=dp[i-1][j]+dp[i][j-1]

#字符串
java中的字符串是immutable，即不可变的。  
解释：已有字符串"abcd"第二次赋值成"abcdef"，不是在原内存地址上修改数据，而是重新指向一个新对象，新地址。
##Rabin-Karp算法
在暴力的基础上利用Hash值来进行判断，hash值相同再去匹配，类似于加了一个布朗过滤器。
##KMP算法
字符串A是否包含字符串B  
移动位数 = 已匹配的字符数 - 对应的部分匹配值
