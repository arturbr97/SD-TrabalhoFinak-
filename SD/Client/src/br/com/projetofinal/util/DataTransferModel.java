package br.com.projetofinal.util;

import java.io.Serializable;
import java.util.Objects;

public class DataTransferModel implements Serializable {

    private String keywords;
    private String text;

    public DataTransferModel(String keywords, String text) {
        this.keywords = keywords;
        this.text = text;
    }

    public DataTransferModel() {
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DataTransferModel{" + "keywords=" + keywords + ", text=" + text + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.keywords);
        hash = 11 * hash + Objects.hashCode(this.text);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataTransferModel other = (DataTransferModel) obj;
        if (!Objects.equals(this.keywords, other.keywords)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

    

}
