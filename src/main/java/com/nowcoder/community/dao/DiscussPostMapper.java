package com.nowcoder.community.dao;


import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param用于给参数取别名，
    // 如果只有一个参数，并且用于动态sql中判断，必须用@Param
    int selectDiscussPostRows(@Param("userId") int userId);

    // 查询最新的10个帖子，显示在首页上
    List<DiscussPost> selectDiscussPostsOrderByTime(int userId, int offset, int limit);




}
