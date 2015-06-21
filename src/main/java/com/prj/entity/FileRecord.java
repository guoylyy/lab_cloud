package com.prj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author yiliang.gyl
 * @version $Id: FileRecord.java, v 0.1 Jun 21, 2015 11:55:00 AM yiliang.gyl Exp $
 */
@Entity
@Table(name = "file")
public class FileRecord extends BaseEntity {

    private String fileName;

    private String metaData;

    private String path;

    private String fileType;

    @Column(nullable = false)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(nullable = true)
    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @Column(nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(nullable = true)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
