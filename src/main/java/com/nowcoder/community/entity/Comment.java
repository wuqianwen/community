package com.nowcoder.community.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String content;
    private int status;
    private Date CreateTime;

    public int getId() {
        return id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", targetId=" + targetId +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", CreateTime=" + CreateTime +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public int getEntityType() {
        return entityType;
    }

    public int getTargetId() {
        return targetId;
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

}
