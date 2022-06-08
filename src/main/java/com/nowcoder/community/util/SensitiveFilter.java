package com.nowcoder.community.util;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    // 替换符
    private static final String REPLACEMENT = "***";

    // 根节点
    TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init(){
        try(
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader read = new BufferedReader(new InputStreamReader(is));
                ) {
            String keyword;
            while((keyword = read.readLine()) != null){
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败："+e.getMessage());
        }

    }

    // 将一个敏感词加入到前缀树中
    private void addKeyword(String keyword){
        TrieNode tempNode = rootNode;
        for(int i =0;i<keyword.length();++i){
            char c = keyword.charAt(i);
            TrieNode subNode = rootNode.getSubNode(c);
            if(subNode == null){
                // 初始化子节点
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            tempNode = subNode;
            if(i==keyword.length()-1){
                tempNode.setKeywordEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     * @param text
     * @return
     */
    public String filter(String text){
        if(StringUtils.isNullOrEmpty(text)){
            return null;
        }
        // 指针1
        TrieNode tempNode = rootNode;
        // 指针2
        int begin = 0;
        // 指针3
        int position = 0;
        // 结果
        StringBuilder sb = new StringBuilder();

        while(begin < text.length()){
            if(position < text.length()){
                char c = text.charAt(position);
                // 跳过符号
                if(this.isSymbol(c)){
                    if(tempNode == rootNode){
                        sb.append(c);
                        begin++;
                    }
                    position++;
                    continue;
                }

                tempNode = tempNode.getSubNode(c);
                if(tempNode == null){
                    sb.append(text.charAt(begin));
                    position= ++begin;
                    tempNode = rootNode;
                }else if(tempNode.isKeywordEnd){
                    sb.append(REPLACEMENT);
                    begin = ++position;
                    tempNode = rootNode;
                }else{
                    position++;
                }
            }else{
                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            }
        }

        return sb.toString();
    }

    // 判断是否为符号
    private boolean isSymbol(Character c){
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    // 前缀树
    private class TrieNode{
        // 关键词结束标识
        private boolean isKeywordEnd = false;

        // 子节点 key:下级字符，value下级节点
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        // 添加子节点
        public void addSubNode(Character c, TrieNode node){
            subNodes.put(c, node);
        }

        // 获取子节点
        public TrieNode getSubNode(Character c){
            return subNodes.get(c);
        }

    }

}
