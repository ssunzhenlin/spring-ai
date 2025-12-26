package com.zhenlin.springai.service;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataLoaderService {

    @Value("classpath:ticket-781.txt")
    private org.springframework.core.io.Resource txtResource;

    @Resource
    private VectorStore vectorStore;

    public void loadDocuments() {
        // 提取: 使用文本读取器
        TextReader txtReader = new TextReader(txtResource);
        List<Document> documents = txtReader.get();

        // 转换：按Token拆分
        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.apply(documents);

        // 加载: 存储到ES向量数据库
        vectorStore.add(chunks);
    }

//    public void loadDocuments() {
//        Path dir = Paths.get("C:\\\\new start");
//        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
//            return;
//        }
//
//        List<Document> documents = new ArrayList<>();
//        try (Stream<Path> stream = Files.walk(dir)) {
//            stream.filter(Files::isRegularFile).forEach(path -> {
//                org.springframework.core.io.Resource fileRes = new FileSystemResource(path.toFile());
//                TextReader txtReader = new TextReader(fileRes);
//                List<Document> fileDocs = txtReader.get();
//                if (fileDocs != null && !fileDocs.isEmpty()) {
//                    documents.addAll(fileDocs);
//                }
//            });
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to read files from `C:\\new start`", e);
//        }
//
//        TokenTextSplitter splitter = new TokenTextSplitter();
//        List<Document> chunks = splitter.apply(documents);
//
//        vectorStore.add(chunks);
//    }
}
